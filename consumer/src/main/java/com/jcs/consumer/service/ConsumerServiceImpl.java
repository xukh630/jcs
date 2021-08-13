package com.jcs.consumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.jcs.consumer.threadPool.ThreadPools;
import com.jcs.provider.service.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @auther xukh
 * @date 2019/8/2 19:37
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    private static final Logger log = LoggerFactory.getLogger(ConsumerServiceImpl.class);

    @Reference
    private ProviderService providerService;

    @Override
    public String get() {
        return "consumer";
    }

    @Override
    public String getProvider() {
        return providerService.get();
    }

    @Override
    public String executeThreadPool() {

        InheritableThreadLocal<Object> local = new InheritableThreadLocal<>();


        try {

            for (int i = 0; i < 10; i++) {
                ThreadPools instance = ThreadPools.getInstance();
                instance.traceIdThreadPool(new Runnable() {
                    @Override
                    public void run() {
                        String traceId = RpcContext.getContext().getAttachment("traceId");
                        log.info("traceId={}", traceId);
                        System.out.println(traceId);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "finish";
    }

    public static void main(String[] args) {

        ThreadLocal threadLocal = new ThreadLocal();

        threadLocal.set("a");
        threadLocal.set("b");

        Runnable run = () -> {
            Thread thread = Thread.currentThread();
            threadLocal.set(new Random().nextInt(10));
            try {
                thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName() + "  " + threadLocal.get());
        };

        Thread thread1 = new Thread(run, "Thread1");
        Thread thread2 = new Thread(run, "thread2");
        thread1.start();
        thread2.start();
    }

}
