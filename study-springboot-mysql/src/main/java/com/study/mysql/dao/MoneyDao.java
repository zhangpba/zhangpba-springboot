package com.study.mysql.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 数据访问层
 *
 * @author zhangpba
 * @date 2021-11-03
 */
@Component
public class MoneyDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 初始化部分数据
     */
    @PostConstruct
    public void init() {
        String sql = "replace into money (id, name, money) values (320, '初始化', 200)," + "(330, '初始化', 200)," +
                "(340, '初始化', 200)," + "(350, '初始化', 200)";
        jdbcTemplate.execute(sql);
    }

    public boolean updateName(int id, String name) {
        String sql = "update money set `name`= '" + name + "' where id=" + id;
        jdbcTemplate.execute(sql);
        return true;
    }

    public void query(String tag, int id) {
        String sql = "select * from money where id=" + id;
        Map map = jdbcTemplate.queryForMap(sql);
        System.out.println(tag + " >>>> " + map);
    }

    public boolean updateMoney(int id) {
        String sql = "update money set `money`= `money` + 10 where id=" + id;
        jdbcTemplate.execute(sql);
        return false;
    }
}
