package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MybatisAnnoTest {
    /**
     * 测试基于注解的mybatis使用
     * @param args
     */
    public static void main(String[] args) throws Exception{
        //1.获取字节输入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.根据输入流构建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.根据SqlSessionFactory生产一个SqlSession
        SqlSession session = factory.openSession(true);
        //4.使用SqlSession获取dao的代理对象
        IUserDao dao = session.getMapper(IUserDao.class);
        //5.执行dao的方法
        List<User> users = dao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();


    }
}
