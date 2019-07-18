package com.lyp.learn.first;

import com.lyp.learn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
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
 * @Description: 入门程序
 * @create: 2019-07-17 09:12
 */
public class MyBatisFirst {

    private static SqlSessionFactory sqlSessionFactory = null;

    /**
     * 在本测试类种
     * 每个被标记 @Test 方法执行之前执行 此方法
     */
    @BeforeEach
    public void prepareCreateSqlSessionFactory() throws IOException {
        String resource = "SqlMapConfig.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }



    /**
     * 根据id查询用户信息，得到一条记录结果
     * @throws IOException
     */
    @Test
    public void testFindUserById() throws IOException {
        SqlSession sqlSession = null;

        try {
            //指定MyBatis配置文件，在类路径下的
            //若是包含在sqlmap文件中，需要 sqlmap/SqlMapConfig.xml 这样写
            String resource = "SqlMapConfig.xml";

            //通过配置文件，获取输入流
            InputStream inputStream = Resources.getResourceAsStream(resource);

            //根据配置文件，创建会话工厂sqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //根据sqlSessionFactory，获取 sqlSession
            sqlSession = sqlSessionFactory.openSession();

            // 通过SqlSession操作数据库
            // 第一个参数：映射文件中statement的id，等于=namespace+"."+statement的id
            // 第二个参数：指定和映射文件中所匹配的parameterType类型的参数
            // sqlSession.selectOne结果 是与映射文件中所匹配的resultType类型的对象
            // selectOne查询出一条记录
            User user = sqlSession.selectOne("test.selectUserById", 25);
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            sqlSession.close();
        }

    }

    /**
     * 根据用户名称模糊查询用户列表
     */
    @Test
    public void testFindUserByName(){
        SqlSession sqlSession = null;
        try {
            sqlSession= sqlSessionFactory.openSession();
            List<User> users = sqlSession.selectList("test.findUserByName","小明");
            System.out.println(users);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null){
                try {
                    sqlSession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据用户名称模糊查询用户列表
     */
    @Test
    public void testFindUserByName2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("test.findUserByName2","%小明%");
        System.out.println(users);
    }

    /**
     * 添加用户信息
     */
    @Test
    public void testInsertUser1(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("李民");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("河南");
        Integer rows = sqlSession.insert("test.insertUser1",user);

        //显示提交，才能在数据库看到插入的数据
        sqlSession.commit();
        System.out.println(rows);
        System.out.println(user);
    }

    /**
     * 添加用户信息
     */
    @Test
    public void testInsertUser2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("诸葛亮");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("卧龙");
        Integer rows = sqlSession.insert("test.insertUser2",user);

        //显示提交，才能在数据库看到插入的数据
        sqlSession.commit();

        System.out.println(rows);
        System.out.println(user);
    }

    /**
     * 添加用户信息
     */
    @Test
    public void testInsertUser3(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("荀攸");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("青山");
        Integer rows = sqlSession.insert("test.insertUser3",user);

        //显示提交，才能在数据库看到插入的数据
        sqlSession.commit();

        System.out.println(rows);
        //获取用户自增的id
        System.out.println("用户自增的id : " + user.getId());
        System.out.println(user);
    }

    /**
     * 删除用户
     */
    @Test
    public void testDeleteUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int rows = sqlSession.delete("test.deleteUserById",34);
        //显示提交
        sqlSession.commit();
        System.out.println(rows);
    }

    /**
     * 更新用户
     */
    @Test
    public void testUpdateUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        //必须设置id
        user.setId(33);
        user.setUsername("aa");
        user.setBirthday(new Date());
        user.setSex("2");
        user.setAddress("商丘");
        int rows = sqlSession.update("test.updateUser",user);

        //显示提交
        sqlSession.commit();
        System.out.println(rows);
    }
}
