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
import java.util.List;

/**
 * 测试Mybatis的CRUD操作
 */
public class UserTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;
    private SqlSessionFactory factory;

    @Before //用于在测试方法之前执行
    public void init() throws Exception {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory对象
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    @After //用于在测试方法执行之后执行
    public void destory() throws Exception {
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testFirstLevelCache() throws Exception {
        //5.执行查询所有方法
        User user1 = userDao.findById(41);
        System.out.println(user1);
//        sqlSession.close();
//        //再次开启
//        sqlSession = factory.openSession(true);
        sqlSession.clearCache();  //此方法也可以清空缓存
        userDao = sqlSession.getMapper(IUserDao.class);
        User user2 = userDao.findById(41);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }

    /**
     * 测试缓存同步
     * 一级缓存是sqlSession范围的缓存，当调用sqlSession的修改，添加，删除，commit(),close()等方法时，就会清空一级缓存
     */
    @Test
    public void testClearCache() throws Exception {
        //1.根据id查询用户
        User user1 = userDao.findById(41);
        System.out.println(user1);
        //2.更新用户信息
        user1.setUsername("update user clear cache");
        user1.setAddress("北京市海淀区");
        userDao.updateUser(user1); //返回false
        //3.再次查询id为41的用户
        userDao = sqlSession.getMapper(IUserDao.class);
        User user2 = userDao.findById(41);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }

}
