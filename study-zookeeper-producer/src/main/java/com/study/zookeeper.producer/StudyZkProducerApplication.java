package com.study.zookeeper.producer;

import com.study.zookeeper.producer.service.Register;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * zk消费启动类
 *
 * @author zhangpba
 * @date 2021-10-13
 */
@SpringBootApplication
public class StudyZkProducerApplication implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    public static void main(String[] args) {
        SpringApplication.run(StudyZkProducerApplication.class, args);
    }

    @Value("${server.port}")
    private Long port;

    @Bean
    public Register register() {
        Register register = new Register();
        register.register("127.0.0.1:" + port);
        return register;
    }

    // TODO 配置端口：配置文件没有生效，在代码中先配置上端口
    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
//        factory.setPort(9006);
    }


}
