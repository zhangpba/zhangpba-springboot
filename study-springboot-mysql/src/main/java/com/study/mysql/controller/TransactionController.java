package com.study.mysql.controller;

import com.study.mysql.service.RcTransactionService;
import com.study.mysql.service.RrTransactionService;
import com.study.mysql.service.RuTransactionService;
import com.study.mysql.service.SerializeTransactionService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事务隔离测试控制器
 *
 * @author zhangpba
 * @date 2021-11-03
 */
@RestController
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    /**
     * 未提交读
     */
    @Autowired
    private RuTransactionService ruTransactionService;

    /**
     * 提交读
     */
    @Autowired
    private RcTransactionService rcTransactionService;

    /**
     * 可重复读
     */
    @Autowired
    private RrTransactionService rrTransactionService;

    /**
     * 串行化
     */
    @Autowired
    SerializeTransactionService serializeTransactionService;

    /**
     * 未提交读
     * ru隔离级别的事务，可能出现脏读，不可避免不可重复读，幻读
     *
     * @param name 参数
     * @return
     */
    @ApiOperation(value = "未提交读")
    @RequestMapping(value = "/testRuIsolation", method = RequestMethod.GET)
    public boolean testRuIsolation(int id, String name) throws Exception {
        // 子线程开启只读事务，主线程执行修改
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ruTransactionService.readRuTransaction(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return ruTransactionService.ruTransaction(id, "ru 读写事务");
    }


    /**
     * 提交读
     * rc隔离级别事务，未提交的写事务，会挂起其他的读写事务；可避免脏读，更新丢失；但不能防止不可重复读、幻读
     *
     * @param name 参数
     * @return
     */
    @ApiOperation(value = "提交读")
    @RequestMapping(value = "/testRcIsolation", method = RequestMethod.GET)
    public boolean testRcIsolation(int id, String name) throws Exception {
        logger.info("提交读:{}", id);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    rcTransactionService.readRcTransaction(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);

        return rcTransactionService.rcTransaction(id, name);
    }

    /**
     * 可重复读
     * rr隔离级别事务，读事务禁止其他的写事务，未提交写事务，会挂起其他读写事务；可避免脏读，不可重复读
     *
     * @param name 参数
     * @return
     */
    @ApiOperation(value = "可重复读")
    @RequestMapping(value = "/testRrIsolation", method = RequestMethod.GET)
    public boolean testRrIsolation(int id, String name) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    rrTransactionService.readRrTransaction(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);

        return rrTransactionService.rrTransaction(id, name);
    }

    /**
     * 串行化
     *
     * @param name 参数
     * @return
     */
    @ApiOperation(value = "串行化")
    @RequestMapping(value = "/testSerializeIsolation", method = RequestMethod.GET)
    public boolean testSerializeIsolation(int id, String name) throws Exception {
        // 子线程开启只读事务，主线程执行修改
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serializeTransactionService.readSerializeTransaction(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);

        return serializeTransactionService.serializeTransaction(id, name);
    }
}

