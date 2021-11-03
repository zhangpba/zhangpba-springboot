package com.study.zookeeper.consumer;

import com.study.zookeeper.consumer.listener.ZookListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * zk消费启动类
 *
 * @author zhangpba
 * @date 2021-10-13
 */
@SpringBootApplication
public class StudyZkConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyZkConsumerApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ZookListener zookListener(){
        ZookListener listener = new ZookListener();
        listener.init();
        return listener;
    }
}
