package com.lyp.learn.dao;

import com.lyp.learn.pojo.User;

import java.util.List;

/**
 * @Author: liyapu
 * @Description: 原始dao 方式开发
 * @create: 2019-07-17 14:43
 */
public interface UserDao {

    /**
     * 根据用户id 查找 user
     * @param id
     * @return
     */
    User selectUserById(int id);

    /**
     * 根据用户名字查询用户
     * @param name
     * @return
     */
    List<User> selectUserByName(String name);

    /**
     * 插入用户
     * @param user
     */
    int insertUser(User user);

    /**
     * 更新用户
     * @param user
     */
    int updateUser(User user);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    int deleteUser(int id);
}
