package com.study.mysql.service;

import com.study.mysql.dao.MoneyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 未提交读：ru隔离级别的事务，可能出现脏读，不可避免不可重复读，幻读
 */
@Service
public class RuTransactionService {

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
    public boolean ruTransaction(int id, String name) throws InterruptedException {
        if (moneyDao.updateName(id, name)) {
            moneyDao.query("事务1：修改后的读取", id);
            Thread.sleep(2000);
        }
        moneyDao.query("事务1：修改后等待2s后读取", id);
        return false;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
    public boolean readRuTransaction(int id) throws InterruptedException {
        moneyDao.query("事务2：", id);
        Thread.sleep(1000);
        moneyDao.query("事务2：", id);
        return true;
    }
}
