package com.study.demo.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 定义配置信息 实体
 *
 * @date 2021-08-29
 */
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {

    private String sayWhat;

    private String toWho;

    public String getSayWhat() {
        return sayWhat;
    }

    public void setSayWhat(String sayWhat) {
        this.sayWhat = sayWhat;
    }

    public String getToWho() {
        return toWho;
    }

    public void setToWho(String toWho) {
        this.toWho = toWho;
    }
}
