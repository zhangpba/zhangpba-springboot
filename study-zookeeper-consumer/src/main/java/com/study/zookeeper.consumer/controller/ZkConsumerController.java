package com.study.zookeeper.consumer.controller;

import com.study.zookeeper.consumer.listener.ZookListener;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 测试zookeeper-consumer接口：zookeeper-consumer提供方
 *
 * @author zhangpba
 * @date 2021-10-13
 */
@Api(value = "zookeeper-consumer消费方")
@RestController
public class ZkConsumerController {
    private static final Logger logger = LoggerFactory.getLogger(ZkConsumerController.class);

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ZookListener listener;


    /**
     * 测试GET请求：消费方
     *
     * @param request 请求参数
     * @return 返回信息
     */
    @ApiOperation(value = "测试post请求：消费方")
    @RequestMapping(value = "/consumer/id", method = RequestMethod.GET)
    public String consumerGet(HttpServletRequest request) {
        logger.info("study-consumer服务，服务端口:{}", request.getLocalPort());

        //从zookeeper中获取调用的ip
        String path = listener.getPath();
        if (path == null) {
            return "对不起，产品暂时停止服务！";
        }
        return restTemplate.getForObject("http://" + listener.getPath() + "/producer/id", String.class);
    }

}
