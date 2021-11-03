package com.study.mysql.service;

import com.study.mysql.dao.MoneyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 提交读：测试不可重复读，一个事务内，两次读取的结果不一样
 */
@Service
public class RcTransactionService {

    @Autowired
    private MoneyDao moneyDao;

    /**
     * 事务一：修改数据
     *
     * @param id
     * @return
     * @throws InterruptedException
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
    public boolean rcTransaction(int id, String name) throws InterruptedException {
        if (moneyDao.updateName(id, name)) {
            moneyDao.query("事务1：修改后的读取", id);
            Thread.sleep(2000);
        }
        moneyDao.query("事务1：修改后等待2s后读取", id);
        return false;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
    public boolean readRcTransaction(int id) throws InterruptedException {
        moneyDao.query("事务2：rc read only", id);
        Thread.sleep(1000);
        moneyDao.query("事务2：rc read only", id);
        Thread.sleep(3000);
        moneyDao.query("事务2：rc read only", id);
        return true;
    }
}
