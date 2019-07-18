package com.lyp.learn.mapper;

import com.lyp.learn.pojo.User;
import com.lyp.learn.pojo.UserCustom;
import com.lyp.learn.pojo.UserQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: liyapu
 * @Description: UserMapper 代理的 测试类
 * @create: 2019-07-17 15:51
 */
class UserMapperTest {
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
    void selectUserById(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectUserById(1);
        System.out.println(user);

        //释放资源
        sqlSession.close();
    }

    @Test
    void selectUserByIds(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(20);
        ids.add(31);
        ids.add(32);

        List<User> users = userMapper.selectUserByIds(ids);
        System.out.println(users);

        //释放资源
        sqlSession.close();
    }

    @Test
    void selectUserByIds2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<Integer> ids = new ArrayList<>();
        ids.add(31);
        ids.add(32);

        List<User> users = userMapper.selectUserByIds2(ids);
        System.out.println(users);

        //释放资源
        sqlSession.close();
    }

    @Test
    void selectUserByArray(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

       int [] ids = {1,30,31,32};
        List<User> users = userMapper.selectUserByArray(ids);
        System.out.println(users);

        //释放资源
        sqlSession.close();
    }


    @Test
    void selectUserByArray2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        int [] ids = {30,31,32};
        List<User> users = userMapper.selectUserByArray2(ids);
        System.out.println(users);

        //释放资源
        sqlSession.close();
    }

    @Test
    void selectUserByIdResultMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectUserByIdResultMap(31);
        System.out.println(user);

        //释放资源
        sqlSession.close();
    }

    @Test
    void findUserByName() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = userMapper.findUserByName("小明");
//        模拟sql注入
//        List<User> users = userMapper.findUserByName("小明' or 1=1 or id like '%");
        System.out.println(users);

        //释放资源
        sqlSession.close();
    }

    @Test
    void findUserByName2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = userMapper.findUserByName2("%小明%");
        System.out.println(users);

        //释放资源
        sqlSession.close();
    }


    @Test
    void findUserByAddresses() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<String> addressList = new ArrayList<>();
        addressList.add("北京");
        addressList.add("青山");
        List<User> users = userMapper.findUserByAddresses(addressList);
        System.out.println(users);

        //释放资源
        sqlSession.close();
    }

    @Test
    void findUserByAddressArray() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        String [] addressArr = {"北京","青山"};
        List<User> users = userMapper.findUserByAddressArray(addressArr);
        System.out.println(users);

        //释放资源
        sqlSession.close();
    }

    @Test
    void findUserList() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //设置查询参数
        UserQueryVo userQueryVo = new UserQueryVo();

        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername("小明");

        userQueryVo.setUserCustom(userCustom);

        List<User> users = userMapper.findUserList(userQueryVo);
        System.out.println(users);

        //释放资源
        sqlSession.close();
    }

    @Test
    void findUserListCount() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //设置查询参数
        UserQueryVo userQueryVo = new UserQueryVo();

        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername("小明");

        userQueryVo.setUserCustom(userCustom);

        int count = userMapper.findUserListCount(userQueryVo);
        System.out.println(count);

        //释放资源
        sqlSession.close();
    }


    @Test
    void findUserList2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //设置查询参数
        UserQueryVo userQueryVo = new UserQueryVo();

        UserCustom userCustom = new UserCustom();
        //动态sql,这里该参数进行测试
//        userCustom.setSex("1");
        userCustom.setUsername("小明");

        userQueryVo.setUserCustom(userCustom);

        List<User> users = userMapper.findUserList2(userQueryVo);
//        List<User> users = userMapper.findUserList2(null);
        System.out.println(users);

        //释放资源
        sqlSession.close();
    }

    @Test
    void findUserListCount2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //设置查询参数
        UserQueryVo userQueryVo = new UserQueryVo();

        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
//        userCustom.setUsername("小明");

        userQueryVo.setUserCustom(userCustom);

        int count = userMapper.findUserListCount2(userQueryVo);
//        int count = userMapper.findUserListCount2(null);
        System.out.println(count);

        //释放资源
        sqlSession.close();
    }



    @Test
    void findUserList3() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //设置查询参数
        UserQueryVo userQueryVo = new UserQueryVo();

        UserCustom userCustom = new UserCustom();
        //动态sql,这里该参数进行测试
//        userCustom.setSex("1");
        userCustom.setUsername("小明");

        userQueryVo.setUserCustom(userCustom);

        List<User> users = userMapper.findUserList3(userQueryVo);
//        List<User> users = userMapper.findUserList3(null);
        System.out.println(users);

        //释放资源
        sqlSession.close();
    }

    @Test
    void findUserListCount3() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //设置查询参数
        UserQueryVo userQueryVo = new UserQueryVo();

        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
//        userCustom.setUsername("小明");

        userQueryVo.setUserCustom(userCustom);

        int count = userMapper.findUserListCount3(userQueryVo);
//        int count = userMapper.findUserListCount3(null);
        System.out.println(count);

        //释放资源
        sqlSession.close();
    }

    @Test
    void findUserList4() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //设置查询参数
        UserQueryVo userQueryVo = new UserQueryVo();

        UserCustom userCustom = new UserCustom();
        //动态sql,这里该参数进行测试
//        userCustom.setSex("1");
        userCustom.setUsername("小明");

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(10);
        ids.add(16);

        userQueryVo.setUserCustom(userCustom);
        userQueryVo.setIds(ids);

        List<User> users = userMapper.findUserList4(userQueryVo);
//        List<User> users = userMapper.findUserList4(null);
        System.out.println(users);

        //释放资源
        sqlSession.close();
    }

    @Test
    void findUserListCount4() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //设置查询参数
        UserQueryVo userQueryVo = new UserQueryVo();

        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
//        userCustom.setUsername("小明");

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(10);
        ids.add(16);

        userQueryVo.setUserCustom(userCustom);
        userQueryVo.setIds(ids);

        int count = userMapper.findUserListCount4(userQueryVo);
//        int count = userMapper.findUserListCount4(null);
        System.out.println(count);

        //释放资源
        sqlSession.close();
    }



    @Test
    void insertUser1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUsername("李民");
        user.setBirthday(new Date());
        user.setSex("2");
        user.setAddress("商丘");

        userMapper.insertUser1(user);

        //显示提交
        sqlSession.commit();

        //释放资源
        sqlSession.close();
    }

    @Test
    void insertUser2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUsername("张三");
        user.setBirthday(new Date());
        user.setSex("2");
        user.setAddress("商丘");

        userMapper.insertUser2(user);

        //显示提交
        sqlSession.commit();

        //释放资源
        sqlSession.close();
    }

    @Test
    void insertUser3() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUsername("王五");
        user.setBirthday(new Date());
        user.setSex("2");
        user.setAddress("商丘");

        userMapper.insertUser3(user);

        //显示提交
        sqlSession.commit();

        //释放资源
        sqlSession.close();
    }

    @Test
    void insertUserBatch() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("王五");
        user1.setBirthday(new Date());
        user1.setSex("2");
        user1.setAddress("商丘");

        User user2 = new User();
        user2.setUsername("操作");
        user2.setBirthday(new Date());
        user2.setSex("2");
        user2.setAddress("许昌");

        User user3 = new User();
        user3.setUsername("关羽");
        user3.setBirthday(new Date());
        user3.setSex("2");
        user3.setAddress("四川");

        users.add(user1);
        users.add(user2);
        users.add(user3);

       int rows =  userMapper.insertUserBatch(users);

        //显示提交
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        System.out.println(rows);

    }

    @Test
    void insertUserBatch2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("王五");
        user1.setBirthday(new Date());
        user1.setSex("2");
        user1.setAddress("商丘");

        User user2 = new User();
        user2.setUsername("曹操");
        user2.setBirthday(new Date());
        user2.setSex("2");
        user2.setAddress("许昌");

        User user3 = new User();
        user3.setUsername("关羽");
        user3.setBirthday(new Date());
        user3.setSex("2");
        user3.setAddress("四川");

        users.add(user1);
        users.add(user2);
        users.add(user3);

        int rows =  userMapper.insertUserBatch2(users);

        //显示提交
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        System.out.println(rows);

    }


    @Test
    void updateUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        //更新时需要设置id
        user.setId(45);
        user.setUsername("张三");
        user.setBirthday(new Date());
        user.setSex("2");
        user.setAddress("河南");

        userMapper.updateUser(user);

        //显示提交
        sqlSession.commit();

        //释放资源
        sqlSession.close();
    }

    @Test
    void deleteUserById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteUserById(43);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    void getUsersByHashMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("id",2);
        hashMap.put("username","王五");
        hashMap.put("address4","河南郑州");

        List<User> users = userMapper.getUsersByHashMap(hashMap);

        sqlSession.close();
        System.out.println(users);
    }

    @Test
    void getUserByManyParam(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = userMapper.getUserByManyParam(2,"王五","青山");

        sqlSession.close();
        System.out.println(users);
    }
}