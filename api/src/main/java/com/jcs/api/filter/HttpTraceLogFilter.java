/**
 * Copyright(C) 2019 Nanjing lichensi Technology Co., Ltd. All rights reserved.
 */
package com.jcs.api.filter;

import com.google.common.collect.Maps;
import com.google.gson.GsonBuilder;
import com.jcs.api.utils.StringUtil;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

/**
 * @author lichensi
 * @date: 2019-07-12 17:15
 * @des: HTTP请求调用日志过滤器
 */
@Component
@Order
public class HttpTraceLogFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(HttpTraceLogFilter.class);

    public static final String TRACE_ID = "traceId";

    private final String IGNORE_CONTENT_TYPE = "multipart/form-data";

    private final String[] IGNORE_PATH_ARRAY = {"/healthCheck"};

    private final String EMPTY_STRING = "";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        MDC.put(TRACE_ID, StringUtil.buildUUID());
        if (!isRequestValid(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }
        long startTime = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        }catch (Exception e){
            log.error("HttpTraceLogFilter.doFilterInternal Exception >>> ", e);
        } finally {
            String path = request.getRequestURI();
            if (!ArrayUtils.contains(IGNORE_PATH_ARRAY,path) && !StringUtils.equals(IGNORE_CONTENT_TYPE, request.getContentType())) {
                HttpTraceLog traceLog = new HttpTraceLog();
                traceLog.setHeaderMap(getHeader(request));
                traceLog.setParameterMap(request.getParameterMap());
                traceLog.setRequestBody(getRequestBody(request));
                traceLog.setResponseBody(getResponseBody(response));
                log.info("traceId={} , reuqestMethod={} , requestUri={} >>> httpStatus={} , {} , spendTime={} ms", MDC.get(TRACE_ID), request.getMethod(), path, response.getStatus() , traceLog, (System.currentTimeMillis() - startTime));
            }
            MDC.clear();
            updateResponse(response);
        }
    }

    private boolean isRequestValid(HttpServletRequest request) {
        try {
            new URI(request.getRequestURL().toString());
            return true;
        } catch (URISyntaxException ex) {
            log.error("RequestURL URISyntaxException >>> ", ex);
            return false;
        }
    }

    private String getRequestBody(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            try {
                return IOUtils.toString(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
            } catch (IOException e) {
                log.error("getRequestBody Exception >>> ", e);
            }
        }
        return EMPTY_STRING;
    }

    private String getResponseBody(HttpServletResponse response) {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (null != wrapper) {
            try {
                return IOUtils.toString(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
            } catch (IOException e) {
                log.error("getResponseBody Exception >>> ", e);
            }
        }
        return EMPTY_STRING;
    }

    private void updateResponse(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        Objects.requireNonNull(responseWrapper).copyBodyToResponse();
    }

    private Map<String,String> getHeader(HttpServletRequest request){
        Map<String,String> headerMap = Maps.newHashMap();
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (null != wrapper) {
            Enumeration headerNames = wrapper.getHeaderNames();
            if(null == headerNames){
                return headerMap;
            }
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                headerMap.put(key, wrapper.getHeader(key));
            }
        }
        return headerMap;
    }

    @Data
    private static class HttpTraceLog {
        private Map<String,String[]> parameterMap;
        private Map<String,String> headerMap;
        private String requestBody;
        private String responseBody;

        @Override
        public String toString() {
            return "HttpTraceLog{" +
                    "parameterMap=" + new GsonBuilder().create().toJson(parameterMap) +
                    ", headerMap=" + headerMap +
                    ", requestBody='" + requestBody + '\'' +
                    ", responseBody='" + responseBody + '\'' +
                    '}';
        }
    }
}
