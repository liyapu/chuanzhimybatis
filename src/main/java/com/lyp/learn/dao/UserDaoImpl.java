package com.lyp.learn.dao;

import com.lyp.learn.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-07-17 14:46
 */
public class UserDaoImpl implements UserDao {
    //需要持有 SqlSessionFactory 对象,不能持有SqlSession(因为SqlSession线程不安全)
    private SqlSessionFactory sqlSessionFactory;

    //通过构造方法注入
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    @Override
    public User selectUserById(int id) {
        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("test.selectUserById",1);

        //释放资源
        sqlSession.close();
        return user;
    }

    @Override
    public List<User> selectUserByName(String name) {
        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("test.findUserByName",name);

        //释放资源
        sqlSession.close();
        return users;
    }

    @Override
    public int insertUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int rows = sqlSession.insert("test.insertUser1",user);
        //显示提交
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        return rows;
    }

    @Override
    public int updateUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int rows = sqlSession.update("test.updateUser",user);
        //显示提交
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        return rows;
    }

    @Override
    public int deleteUser(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int rows =  sqlSession.delete("test.deleteUserById",id);
        //显示提交
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        return rows;
    }
}
