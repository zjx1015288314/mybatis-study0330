package com.itheima.mybatis;

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

/**
 * 测试Mybatis的CRUD操作
 */
public class SecondLevelCacheTest {
    private InputStream in;
    private SqlSessionFactory factory;

    @Before //用于在测试方法之前执行
    public void init() throws Exception {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory对象
        factory = new SqlSessionFactoryBuilder().build(in);
    }
    @After //用于在测试方法执行之后执行
    public void destory() throws Exception {
        //6.释放资源
        in.close();
    }

    /**
     * 测试二级缓存，其中存储的是数据而不是对象
     */
    @Test
    public void testFirstLevelCache() throws Exception {
        SqlSession sqlSession1 = factory.openSession(true);
        IUserDao dao1 = sqlSession1.getMapper(IUserDao.class);
        User user1 = dao1.findById(41);
        System.out.println(user1);
        sqlSession1.close();  //清空一级缓存
        SqlSession sqlSession2 = factory.openSession(true);
        IUserDao dao2 = sqlSession2.getMapper(IUserDao.class);
        User user2 = dao2.findById(41);
        sqlSession2.close();    //清空一级缓存
        System.out.println(user1 == user2);  //false
    }

}
