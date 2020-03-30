package com.itheima.dao;

import com.itheima.domain.QueryVo;
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
     * 根据Id查询用户信息
     */
    User findById(Integer userId);
    /**
     * 根据名称模糊查询用户信息
     */
    List<User> findByName(String username);
    /**
     * 根据queryVo的条件查询用户
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入的参数条件查询
     * @param user 查询的条件,有可能有用户名，有可能有性别，也有可能有地址，或者都有
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据QueryVo中提供的id集合，查询用户信息
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);
}
