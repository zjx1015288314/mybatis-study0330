package com.itheima.mybatis;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
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
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    @After //用于在测试方法执行之后执行
    public void destory() throws Exception {
        //提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
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
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testfindUserByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        //5.执行模糊查询
        List<User> users = userDao.findUserByVo(vo);
//        List<User> users = userDao.findByName("王");   //statement的字符串拼接
        for (User u : users) {
            System.out.println(u);
        }
    }
    /**
     * 测试if和where标签使用
     * @return
     */
    @Test
    public void findUserByCondition(){
        User u = new User();
        u.setUserName("老王");
        //5.执行模糊查询
        List<User> users = userDao.findUserByCondition(u);
        for (User user : users) {
            System.out.println(user);
        }
    }
    /**
     * 测试foreach标签的使用
     * @return
     */
    @Test
    public void findUserInIds(){
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(42);
        list.add(43);
        list.add(47);
        list.add(49);
        vo.setIds(list);
        //5.执行模糊查询
        List<User> users = userDao.findUserInIds(vo);
        for (User user : users) {
            System.out.println(user);
        }
    }

}
