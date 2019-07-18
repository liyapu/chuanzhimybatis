package com.lyp.learn.mapper;

import com.lyp.learn.pojo.User;
import com.lyp.learn.pojo.UserQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: liyapu
 * @Description: mapper ，相当于dao接口
 * @create: 2019-07-17 14:43
 */
public interface UserMapper {

    /**
     * 根据用户id 查找 user
     * @param id
     * @return
     */
    User selectUserById(int id);

    /**
     * 根据 ids 查询多个用户
     * @param ids
     * @return
     */
    List<User> selectUserByIds(List<Integer> ids);

    /**
     * 根据 ids 查询多个用户
     * @param ids
     * @return
     */
    List<User> selectUserByIds2(@Param("ids") List<Integer> ids);

    /**
     * 通过数组，查询多个用户
     * @param ids
     * @return
     */
    List<User> selectUserByArray(int [] ids);

    /**
     * 通过数组，查询多个用户
     * @param ids
     * @return
     */
    List<User> selectUserByArray2(@Param("ids") int [] ids);

    /**
     * 根据用户id 查找 user
     * @param id
     * @return
     */
    User selectUserByIdResultMap(int id);

    /**
     * 根据用户名字查询用户
     * @param name
     * @return
     */
    List<User> findUserByName(String name);

    /**
     * 根据用户名字查询用户
     * @param name
     * @return
     */
    List<User> findUserByName2(String name);

    /**
     * 通过多个地址，查询多个用户
     * @param addresses
     * @return
     */
    List<User> findUserByAddresses(List<String> addresses);

    /**
     * 通过多个地址，查询多个用户
     * @param addressArr
     * @return
     */
    List<User> findUserByAddressArray(@Param("addressArr") String [] addressArr);

    /**
     * 用户综合信息查询
     * 复杂多条件的查询
     * @param userQueryVo
     * @return
     */
    List<User> findUserList(UserQueryVo userQueryVo);

    /**
     * 用户综合信息查询 总数
     * 复杂多条件的查询
     * @param userQueryVo
     * @return
     */
    int findUserListCount(UserQueryVo userQueryVo);


    /**
     * 用户综合信息查询
     * 复杂多条件的查询
     * @param userQueryVo
     * @return
     */
    List<User> findUserList2(UserQueryVo userQueryVo);

    /**
     * 用户综合信息查询 总数
     * 复杂多条件的查询
     * @param userQueryVo
     * @return
     */
    int findUserListCount2(UserQueryVo userQueryVo);


    /**
     * 用户综合信息查询
     * 复杂多条件的查询
     * @param userQueryVo
     * @return
     */
    List<User> findUserList3(UserQueryVo userQueryVo);

    /**
     * 用户综合信息查询 总数
     * 复杂多条件的查询
     * @param userQueryVo
     * @return
     */
    int findUserListCount3(UserQueryVo userQueryVo);

    /**
     * 用户综合信息查询
     * 复杂多条件的查询
     * @param userQueryVo
     * @return
     */
    List<User> findUserList4(UserQueryVo userQueryVo);

    /**
     * 用户综合信息查询 总数
     * 复杂多条件的查询
     * @param userQueryVo
     * @return
     */
    int findUserListCount4(UserQueryVo userQueryVo);

    /**
     * 插入用户
     * @param user
     */
    int insertUser1(User user);

    /**
     * 插入用户
     * @param user
     */
    int insertUser2(User user);

    /**
     * 插入用户
     * @param user
     */
    int insertUser3(User user);

    /**
     * 插入用户
     * @param user
     */
    int insertUser4(User user);

    /**
     * 批量插入用户
     * @param users
     * @return
     */
    int insertUserBatch( List<User> users);

    /**
     * 批量插入用户
     * @param users
     * @return
     */
    int insertUserBatch2(@Param("users") List<User> users);


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
    int deleteUserById(int id);


    /**
     * 获取用户，通过 hashMap 传参
     * @param hashMap
     * @return
     */
    List<User> getUsersByHashMap(HashMap hashMap);

    /**
     * 通过多参数，查询用户列表
     * 通过注解的方式，@Param("xx") 中指定的参数xx 与xml中使用的一致
     * @param id
     * @param username
     * @param address
     * @return
     */
    List<User> getUserByManyParam(@Param("id") int id,@Param("un") String username,@Param("address") String address);

}
