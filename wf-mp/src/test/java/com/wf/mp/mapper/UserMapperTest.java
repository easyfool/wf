package com.wf.mp.mapper;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.StopWatch;
import com.wf.mp.WfMpApp;
import com.wf.mp.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 12:20
 */
@SpringBootTest(classes = WfMpApp.class)
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    /**
     * 逐条插入1w条数据，耗时 34165050400 ns
     * <p>
     * 每条语句都连接数据库
     */
    @Test
    public void testNormalInsert1w() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setUsername("user" + i);
            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

    }

    /**
     * batch插入1w条数据，耗时 42203149600 ns
     * 每1000条语句连接一次，
     * 但提交到数据库后一条语句一条语句执行
     * insert into t_user values  ()
     * insert into t_user values  ()
     * insert into t_user values  ()
     * ...
     * insert into t_user values  (),(),()...()
     */
    @Test
    public void testBatchInsert1w() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setUsername("user" + i);
            users.add(user);
            if (users.size() == 1000) {
                userMapper.insert(users);
                users.clear();
            }
        }
        //插入剩余条数
        if (CollectionUtil.isNotEmpty(users)) {
            userMapper.insert(users);
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    /**
     * ★★★★★
     * ★需要在参数中打开mysql的批量插入模式，否则不会变快
     * rewriteBatchedStatements=true
     * url: jdbc:mysql://localhost:3306/mp_db?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&allowMultiQueries=true
     * batch插入1w条数据，耗时 726054900 ns
     * 每1000条语句连接一次，
     * 但提交到数据库后每1000执行一个语句
     * <p>
     * insert into t_user values  (),(),()...()
     */
    @Test
    public void test2BatchInsert1w() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setUsername("user" + i);
            users.add(user);
            if (users.size() == 1000) {
                userMapper.insert(users);
                users.clear();
            }
        }
        //插入剩余条数
        if (CollectionUtil.isNotEmpty(users)) {
            userMapper.insert(users);
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}