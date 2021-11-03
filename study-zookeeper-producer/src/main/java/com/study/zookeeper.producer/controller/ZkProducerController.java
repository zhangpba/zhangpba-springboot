package com.study.zookeeper.producer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试zookeeper-producer接口：zookeeper-producer提供方
 *
 * @author zhangpba
 * @date 2021-10-13
 */
@Api(value = "zookeeper-producer提供方")
@RestController
public class ZkProducerController {
    private static final Logger logger = LoggerFactory.getLogger(ZkProducerController.class);

    /**
     * 测试GET请求：消费方
     *
     * @param request 请求参数
     * @return
     */
    @ApiOperation(value = "测试post请求：提供方")
    @RequestMapping(value = "/producer/id", method = RequestMethod.GET)
    public String producerGet(HttpServletRequest request) {
        logger.info("study-producer服务，服务端口:{}", request.getLocalPort());
        return "study-producer服务，服务端口:" + request.getLocalPort();
    }

    /**
     * 测试GET请求：消费方
     *
     * @param request 请求参数
     * @return
     */
    @ApiOperation(value = "测试index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        logger.info("study-producer服务，服务端口:{}", request.getLocalPort());
        return "study-producer服务，服务端口:" + request.getLocalPort();
    }
}
