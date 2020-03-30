package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 在mybatis中针对，CRUD一共有四个注解
 * @Select @Insert @Update @Delete
 */
public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     */
    //当注解只有一个value值需要赋值时，value可省略
//    @Select(value = "select * from user")
    @Select("select * from user")
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);
    /**
     * 删除用户
     * @param userId
     */
    @Update("delete from user where id=#{id}")
    void deleteUser(Integer userId);

    /**
     * 根据id查询用户
     * @param userId
     */
    @Select("select * from user where id=#{id}")
    User findUserById(Integer userId);
    /**
     * 根据用户名称模糊查询用户
     * @param username
     */
//    @Select("select * from user where username like #{username}")
    @Select("select * from user where username like '%${value}%'")
    List<User> findUserByUsername(String username);
}
