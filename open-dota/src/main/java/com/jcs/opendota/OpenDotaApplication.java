package com.jcs.opendota;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
@MapperScan("com.jcs.opendota.mapper.*")
public class OpenDotaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenDotaApplication.class, args);
    }

}
