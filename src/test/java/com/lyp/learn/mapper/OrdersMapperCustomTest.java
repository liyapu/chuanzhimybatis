package com.lyp.learn.mapper;

import com.lyp.learn.pojo.Orders;
import com.lyp.learn.pojo.OrdersCustom;
import com.lyp.learn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-07-18 17:57
 */
class OrdersMapperCustomTest {

    SqlSessionFactory sqlSessionFactory = null;

    @BeforeEach
    void setUp() throws IOException {
        //指定MyBatis配置文件，在类路径下的
        String resource = "SqlMapConfig.xml";

        //通过配置文件，获取输入流
        InputStream in = Resources.getResourceAsStream(resource);

        //根据配置文件，创建会话工厂sqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

    }

    @Test
    void findOrdersUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
        List<OrdersCustom> ordersCustomList = ordersMapperCustom.findOrdersUser();
        System.out.println(ordersCustomList);
        sqlSession.close();
    }

    @Test
    void findOrdersUserResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
        List<Orders> ordersList = ordersMapperCustom.findOrdersUserResultMap();
        System.out.println(ordersList);
        sqlSession.close();
    }

    @Test
    void findOrdersAndOrderDetailResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
        List<Orders> ordersList = ordersMapperCustom.findOrdersAndOrderDetailResultMap();
        System.out.println(ordersList);
        sqlSession.close();
    }

    @Test
    void findUserAndItemsResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
        List<User> userList = ordersMapperCustom.findUserAndItemsResultMap();
        System.out.println(userList);
        sqlSession.close();
    }

    @Test
    void findOrdersUserLazyLoading() {
    }
}