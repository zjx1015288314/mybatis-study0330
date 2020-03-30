package com.itheima.test;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Account;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IAccountDao accountDao;

    @Before
    public void init() throws Exception{
        //1.获取字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.根据输入流构建SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.根据SqlSessionFactory生产一个SqlSession
        session = factory.openSession(true);
        //4.使用SqlSession获取dao的代理对象
        accountDao = session.getMapper(IAccountDao.class);
    }

    @After
    public void destory() throws Exception{
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        //立即加载：在加载账户信息时同时加载同属的用户信息，适合一(多)对一映射
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println("--------每个账户的信息--------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }
//    @Test
//    public void testFindOne(){
//        User user = accountDao.findUserById(49);
//        System.out.println(user);
//    }
//    @Test
//    public void testFindByname(){
////        List<User> users = userDao.findUserByUsername("%mybatis%");
//        List<User> users = accountDao.findUserByUsername("mybatis");
//        for (User user : users) {
//            System.out.println(user);
//        }
//
//    }
}
