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


    // 查询订单关联查询用户，用户信息使用延迟加载
    @Test
    public void testFindOrdersUserLazyLoading() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();// 创建代理对象
        OrdersMapperCustom ordersMapperCustom = sqlSession
                .getMapper(OrdersMapperCustom.class);
        // 查询订单信息（单表）
        List<Orders> list = ordersMapperCustom.findOrdersUserLazyLoading();

        // 遍历上边的订单列表
        for (Orders orders : list) {
            //可以在这里打断点，看日志
            // 执行getUser()去查询用户信息，这里实现按需加载
            User user = orders.getUser();
            System.out.println(user);
        }

    }

    // 一级缓存测试
    @Test
    public void testCacheOneLevel1() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();// 创建代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 下边查询使用一个SqlSession
        // 第一次发起请求，查询id为30的用户
        User user1 = userMapper.selectUserById(30);
        System.out.println(user1);

        // 第二次发起请求，查询id为1的用户,由于是同一个session则不再向数据发出语句直接从缓存取出
        User user2 = userMapper.selectUserById(30);
        System.out.println(user2);

        sqlSession.close();
    }

    // 一级缓存测试
    @Test
    public void testCacheOneLevel2() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();// 创建代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 下边查询使用一个SqlSession
        // 第一次发起请求，查询id为37的用户
        User user1 = userMapper.selectUserById(37);
        System.out.println(user1);

        // 如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，
        // 这样做的目的为了让缓存中存储的是最新的信息，避免脏读。

        // 更新user1的信息
         user1.setUsername("诸葛亮2");
         userMapper.updateUser(user1);
         //执行commit操作去清空缓存
         sqlSession.commit();

        // 第二次发起请求，查询id为37的用户
        User user2 = userMapper.selectUserById(37);
        System.out.println(user2);

        sqlSession.close();

    }

    // 二级缓存测试
    @Test
    public void testCacheSecondLevel1() throws Exception {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        // 创建代理对象
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        // 第一次发起请求，查询id为30的用户
        User user1 = userMapper1.selectUserById(30);
        System.out.println(user1);

        //这里执行关闭操作，将sqlsession中的数据写到二级缓存区域
        sqlSession1.close();

        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        // 第二次发起请求，查询id为30的用户
        User user2 = userMapper2.selectUserById(30);
        System.out.println(user2);

        sqlSession2.close();

    }


    // 二级缓存测试
    @Test
    public void testCacheSecondLevel2() throws Exception {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        // 创建代理对象
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        // 第一次发起请求，查询id为37的用户
        User user1 = userMapper1.selectUserById(37);
        System.out.println(user1);

        //这里执行关闭操作，将sqlsession中的数据写到二级缓存区域
        sqlSession1.close();

		//使用sqlSession3执行commit()操作
		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
		User user  = userMapper3.selectUserById(37);
		user.setUsername("张明明");
		userMapper3.updateUser(user);
		//执行提交，清空UserMapper下边的二级缓存
		sqlSession3.commit();
		sqlSession3.close();

        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        // 第二次发起请求，查询id为37的用户
        User user2 = userMapper2.selectUserById(37);
        System.out.println(user2);

        sqlSession2.close();
    }
}