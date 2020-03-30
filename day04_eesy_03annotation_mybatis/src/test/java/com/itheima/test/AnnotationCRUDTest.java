package com.itheima.test;

import com.itheima.dao.IUserDao;
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

public class AnnotationCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws Exception{
        //1.获取字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.根据输入流构建SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.根据SqlSessionFactory生产一个SqlSession
        session = factory.openSession(true);
        //4.使用SqlSession获取dao的代理对象
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws Exception{
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("mybatis annotation");
        user.setAddress("北京市昌平区");
        userDao.saveUser(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(51);
        user.setUsername("mybatis annotation update");
        user.setAddress("北京市海淀区");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.updateUser(user);
    }
    @Test
    public void testDelete(){
        userDao.deleteUser(51);
    }
    @Test
    public void testFindOne(){
        User user = userDao.findUserById(50);
        System.out.println(user);
    }
    @Test
    public void testFindByname(){
//        List<User> users = userDao.findUserByUsername("%mybatis%");
        List<User> users = userDao.findUserByUsername("mybatis");
        for (User user : users) {
            System.out.println(user);
        }

    }
}
