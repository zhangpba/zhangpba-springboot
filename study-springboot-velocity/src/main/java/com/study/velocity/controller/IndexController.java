package com.study.velocity.controller;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试velocity
 *
 * @author zhangpba
 * @date 2021-09-04
 */
@RestController
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private VelocityEngine velocityEngine;

    /**
     * 测试velocity
     *
     * @param name 参数
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(String name) {
        logger.info("study-velocity服务，参数:{}", name);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("time", format.format(new Date()));
        model.put("message", "这是测试的内容。。。");
        model.put("toUserName", "张三");
        model.put("fromUserName", "老许");
        String massage = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "welcome.vm", "UTF-8", model);
        System.out.println(massage);
        return massage;
    }

}
