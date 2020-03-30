package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户
     */
    void saveUser(User user);
    /**
     * 更新用户
     */
    void updateUser(User user);
    /**
     * 根据Id删除用户
     */
    void deleteUser(Integer userId);
    /**
     * 根据Id查询用户信息
     */
    User findById(Integer userId);
    /**
     * 根据名称模糊查询用户信息
     */
    List<User> findByName(String username);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();

}
