package com.study.base.controller;

import com.study.demo.starter.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试starter
 *
 * @author zhangpba
 * @date 2021-08-29
 */
@RestController
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    //    @Resource(name = "demo")
    @Autowired
    private DemoService demoService;

    /**
     * 测试get请求：消费方
     *
     * @param name 参数
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getFile(String name) {
        String say = demoService.say();
        logger.info(say);
        return say;
    }

}
