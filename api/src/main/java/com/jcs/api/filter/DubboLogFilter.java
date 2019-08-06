package com.jcs.api.filter;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuluokang
 * @date: 2019-07-08 17:39
 * @des: dubbo接口调用日志过滤器
 */
public class DubboLogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(DubboLogFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = getTraceId();
        String rpcName = invocation.getInvoker().getInterface().getName() + "." + invocation.getMethodName();
        logger.info("traceId={} >>> InterfaceName={} , Parameter={}", traceId, rpcName, JSON.toJSONString(invocation.getArguments()));
        //开始时间
        long startTime = System.currentTimeMillis();
        //执行接口调用逻辑
        Result result = invoker.invoke(invocation);
        //调用耗时
        long elapsed = System.currentTimeMillis() - startTime;
        //如果发生异常 则打印异常日志
        if (result.hasException() && invoker.getInterface() != GenericService.class) {
            logger.error("traceId={} InterfaceName={} dubbo执行异常: {}", traceId, rpcName, result.getException());
        } else {
            logger.info("traceId={} >>> InterfaceName={} , Resposne={} , SpendTime={} ms", traceId, rpcName, JSON.toJSONString(new Object[]{result.getValue()}), elapsed);
        }
        //返回结果响应结果
        return result;
    }

    private String getTraceId(){

        String traceId = MDC.get(HttpTraceLogFilter.TRACE_ID);
        if(StringUtils.isNotBlank(traceId)){
            Map<String,String> aAttachments = new HashMap<>();
            aAttachments.put(HttpTraceLogFilter.TRACE_ID,traceId);
            RpcContext.getContext().setAttachments(aAttachments);
        }else{
            MDC.put(HttpTraceLogFilter.TRACE_ID,traceId);
        }
        return traceId;
    }
}
