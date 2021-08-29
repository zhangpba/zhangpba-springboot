package com.study.demo.starter.service;

/**
 * 定义一个service
 *
 * @date 2021-08-29
 */
public class DemoService {

    public String sayWhat;

    public String toWho;


    public DemoService(String sayWhat, String toWho) {
        this.sayWhat = sayWhat;
        this.toWho = toWho;
    }

    public String say() {
        return this.sayWhat + "! " + toWho;
    }
}
