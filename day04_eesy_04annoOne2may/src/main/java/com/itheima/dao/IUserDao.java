package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 在mybatis中针对，CRUD一共有四个注解
 * @Select @Insert @Update @Delete
 */
@CacheNamespace(blocking = true)          //开启二级缓存
public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     */
    //当注解只有一个value值需要赋值时，value可省略
//    @Select(value = "select * from user")
    @Select("select * from user")
    @Results(id = "userMap",value = {
            @Result(id = true,property = "userId",column = "id"),
            @Result(property = "userName",column = "username"),
            @Result(property = "userAddress",column = "address"),
            @Result(property = "userSex",column = "sex"),
            @Result(property = "userBirthday",column = "birthday"),
            @Result(property = "accounts",column = "id",
                    many = @Many(select = "com.itheima.dao.IAccountDao.findAccountByUid",
                    fetchType = FetchType.LAZY))
    })
    List<User> findAll();
    /**
     * 根据id查询用户
     * @param userId
     */
    @Select("select * from user where id=#{id}")
//    @ResultMap(value = {"userMap"})
    @ResultMap("userMap")
    User findUserById(Integer userId);
    /**
     * 根据用户名称模糊查询用户
     * @param username
     */
    @Select("select * from user where username like #{username}")
    @ResultMap("userMap")
    List<User> findUserByUsername(String username);
}
