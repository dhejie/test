package dao;

import been.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCTools;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl {
   //查询user表所有用户 返回ulist
    public List<User> queryAllUser() {
        DataSource ds = JDBCTools.getDataSource();
        QueryRunner run = new QueryRunner(ds);
        List<User> ulist = new ArrayList<>();
        try {
            ulist = run.query("select * from user", new BeanListHandler<User>(User.class), new Object[]{});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ulist;
    }

    //插入user一条数据 返回值自增id的值
    public Object inserUser(User user) {

        DataSource ds = JDBCTools.getDataSource();
        QueryRunner run = new QueryRunner(ds);
        String sql="insert into user (username, account,password, email, status, createtime, last_login_time) values (?,?,?,?,?,?,?)";
        ArrayHandler rsh=new ArrayHandler();
        Object[] params =new Object[]{
         user.getUsername(),
         user.getAccount(),
         user.getPassword(),
         user.getEmail(),
         user.getStatus(),
         new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        };
        try {
          Object[] rest=  run.insert(sql,rsh,params);
           //Object id=  rest[0];
          //return id;
            return rest[0];
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return null;
    }

    //根据用户的id查询user 返回一条数据 一个user对象
    public User queryUserById(Integer id) {
        DataSource ds = JDBCTools.getDataSource();
        QueryRunner runner = new QueryRunner(ds);
        User result = null;
        try {
            result = runner.query("select * from user where id = ? limit 1",
                    new BeanHandler<User>(User.class), new Object[]{ id });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //根据用户账号查询查询用户信息 返回一个user对象
    public User queryUserByAccount(String account) {
        DataSource ds = JDBCTools.getDataSource();
        QueryRunner runner = new QueryRunner(ds);
        User result = null;
        try {
            result = runner.query("select * from user where account = ? limit 1",
                    new BeanHandler<User>(User.class), new Object[]{ account });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//更新用户信息 返回值是更新的行数，通过id更新最后登陆时间
    public Integer updateLastLoginTime(User user) {
    DataSource ds = JDBCTools.getDataSource();
    QueryRunner runner = new QueryRunner(ds);
    // 要执行的插入语句
    String sql = "update user set last_login_time = ? where id = ?";
    // 用于接收插入后自增的id

    // 设置语句中的参数
    Object[] ojebs = new Object[]{
            new Timestamp(System.currentTimeMillis()),
            user.getId()
    };
    try {
        return runner.update(sql, ojebs);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    // 如果走到这里，说明已经进过catch块了，没有正常更新
    return null;
}



}