package com.study.mysql.service;

import com.study.mysql.dao.MoneyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 可重复读
 */
@Service
public class RrTransactionService {

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
    public boolean rrTransaction(int id, String name) throws InterruptedException {
        if (moneyDao.updateName(id, name)) {
            moneyDao.query("事务1：修改后的读取", id);
//            if (moneyDao.updateMoney(id)) {
//                return true;
//            }
        }
        moneyDao.query("事务1：修改后等待2s后读取", id);
        return false;
    }

    /**
     * 只读事务，主要目的是为了隔离其他事务的修改，对本次操作的影响；
     *
     * 比如在某些耗时的涉及多次表的读取操作中，为了保证数据一致性，这个就有用了； 开启只读事务之后，不支持修改数据
     */
    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
    public boolean readRrTransaction(int id) throws InterruptedException {
        moneyDao.query("事务2：", id);
        Thread.sleep(1000);
        moneyDao.query("事务2：", id);
        return true;
    }
}
