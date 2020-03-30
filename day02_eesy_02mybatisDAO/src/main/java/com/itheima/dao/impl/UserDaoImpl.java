package com.itheima.dao.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public List<User> findAll() {
        //1.根据factory获取Sqlsession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法,实现查询列表
        List<User> users = session.selectList("com.itheima.dao.IUserDao.findAll"); //参数就是能获取配置信息的key
        //3.释放资源
        session.close();
        return users;
    }

    public void saveUser(User user) {
        //1.根据factory获取Sqlsession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法,实现保存
        session.insert("com.itheima.dao.IUserDao.saveUser",user); //记得加第二个参数，否则username为空
        //3.提交事务
        session.commit();
        //4.释放资源
        session.close();
    }

    public void updateUser(User user) {
        //1.根据factory获取Sqlsession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法,实现更新
        session.update("com.itheima.dao.IUserDao.updateUser",user); //记得加第二个参数，否则username为空
        //3.提交事务
        session.commit();
        //4.释放资源
        session.close();
    }

    public void deleteUser(Integer userId) {
        //1.根据factory获取Sqlsession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法,实现更新(DefaultSqlSession中CUD操作底层都是调用的update(String statement,Object parameter))
        session.update("com.itheima.dao.IUserDao.deleteUser",userId); //记得加第二个参数，否则username为空
        //3.提交事务
        session.commit();
        //4.释放资源
        session.close();
    }

    public User findById(Integer userId) {
        //1.根据factory获取Sqlsession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法,实现查询列表
        User user = session.selectOne("com.itheima.dao.IUserDao.findById",userId); //参数就是能获取配置信息的key
        //3.释放资源
        session.close();
        return user;
    }

    public List<User> findByName(String username) {
        //1.根据factory获取Sqlsession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法,实现查询列表
        List<User> users = session.selectList("com.itheima.dao.IUserDao.findByName",username); //参数就是能获取配置信息的key
        //3.释放资源
        session.close();
        return users;
    }

    public int findTotal() {
        //1.根据factory获取Sqlsession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法,实现查询列表
        Integer count = session.selectOne("com.itheima.dao.IUserDao.findTotal"); //参数就是能获取配置信息的key
        //3.释放资源
        session.close();
        return count;
    }
}
