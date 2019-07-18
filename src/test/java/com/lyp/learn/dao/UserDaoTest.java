package com.lyp.learn.dao;

import com.lyp.learn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @Author: liyapu
 * @Description: UserDao 的单元测试类
 * @create: 2019-07-17 14:57
 */
class UserDaoTest {

    //用户数据库操作的dao
    UserDao userDao = null;

    @BeforeEach
    void setUp() throws IOException {
        //指定MyBatis配置文件，在类路径下的
        String resource = "SqlMapConfig.xml";

        //通过配置文件，获取输入流
        InputStream in = Resources.getResourceAsStream(resource);

        //根据配置文件，创建会话工厂sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

        //根据sqlSessionFactory，创建 UserDao
        userDao = new UserDaoImpl(sqlSessionFactory);
    }

    @Test
    void selectUserById() {
        User user = userDao.selectUserById(1);
        System.out.println(user);
    }

    @Test
    void selectUserByName() {
        List<User> users = userDao.selectUserByName("小明");
        System.out.println(users);
    }


    @Test
    void insertUser() {
        User user = new User();
        user.setUsername("李民");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("河南");

        int rows = userDao.insertUser(user);

        System.out.println(rows);
    }

    @Test
    void updateUser() {
        User user = new User();
        //必须设置id
        user.setId(40);
        user.setUsername("aa");
        user.setBirthday(new Date());
        user.setSex("2");
        user.setAddress("商丘");

        userDao.updateUser(user);
    }

    @Test
    void deleteUser() {
        userDao.deleteUser(39);
    }
}