package com.itheima.mybatis;

import com.itheima.dao.IUserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 测试Mybatis的CRUD操作
 */
public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before //用于在测试方法之前执行
    public void init() throws Exception {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂对象,创建dao对象
        userDao = new UserDaoImpl(factory);
    }
    @After //用于在测试方法执行之后执行
    public void destory() throws Exception {
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() throws Exception {
        //5.执行查询所有方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("dao impl user");
        user.setAddress("北京市顺义区");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存之前："+user);
        //5.执行添加用户方法
        userDao.saveUser(user);
        System.out.println("保存之后："+user);
    }
    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(47);
        user.setUsername("userdaoimpl update user");
        user.setAddress("北京市朝阳区");
        user.setSex("女");
        user.setBirthday(new Date());
        //5.执行更新
        userDao.updateUser(user);
    }
    /**
     * 测试删除操作
     */
    @Test
    public void testDelete() {
        //5.执行删除
        userDao.deleteUser(50);
    }
    /**
     * 测试查询操作
     */
    @Test
    public void testFindOne() {
        //5.执行查询一个操作
        User user = userDao.findById(47);
        System.out.println(user);
    }
    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName() {
        //5.执行模糊查询
        List<User> users = userDao.findByName("%王%");
//        List<User> users = userDao.findByName("王");   //statement的字符串拼接
        for (User user : users) {
            System.out.println(user);
        }

    }
    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal() {
        //5.执行查询
        int count = userDao.findTotal();
        System.out.println(count);
    }

}
