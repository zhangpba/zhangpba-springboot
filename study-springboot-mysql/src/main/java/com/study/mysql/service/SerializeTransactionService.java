package com.study.mysql.service;

import com.study.mysql.dao.MoneyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 串行化
 */
@Service
public class SerializeTransactionService {

    @Autowired
    private MoneyDao moneyDao;

    /**
     * 事务一：修改数据
     *
     * @param id
     * @return
     * @throws InterruptedException
     */
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public boolean serializeTransaction(int id, String name) throws InterruptedException {
        if (moneyDao.updateName(id, name)) {
            moneyDao.query("事务1：修改后的读取", id);
            Thread.sleep(2000);
        }
        moneyDao.query("事务1：修改后等待2s后读取", id);
        return false;
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public boolean readSerializeTransaction(int id) throws InterruptedException {
        moneyDao.query("事务2：serialize read only", id);
        Thread.sleep(3000);
        moneyDao.query("事务2：serialize read only", id);
        return true;
    }
}
