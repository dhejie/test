package testshop2.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import testshop2.bean.User;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Component//此注解可以标注这个类为一个bean  会自动被ioc容器管理
public class UserDaoImpl {
    @Autowired
    @Qualifier("ds")//ds对应bean注解的ds
    private DataSource dataSource;
//查询全部数据
public List<User> queryAllDate(){
    QueryRunner run =new QueryRunner(dataSource);
    String sql="select * from user where status=1";
    ResultSetHandler<List<User>> rsh=new BeanListHandler(User.class);
    List<User> ulist=new ArrayList<>();
    try {
       ulist= run.query(sql,rsh);
    } catch (SQLException e) {
        System.err.println("获取数据失败！");
    }
    return ulist;
}
//真删除一条数据  通过id删除
public String deleteOneDate(Integer id){
    QueryRunner run=new QueryRunner(dataSource);
    String sql="delete from user where id="+id;
    int row=0;
    try {
       row= run.update(sql);
    } catch (SQLException e) {
        System.err.println("获取数据失败！");
    }
    return "删除了"+row+"行数据！";
}
//修改一条数据
public String updateOneDate(Integer id,String username, String account
            , String password, String email, Integer status,
            Timestamp createtime, Timestamp last_login_time){
        QueryRunner run=new QueryRunner(dataSource);
        String sql="update user set username=?,account=?,password=?,email=?,status=?,createtime=?,last_login_time=? where id="+id;
        Object[] parms={username,account,password,email,status,createtime,last_login_time};
        int row=0;
        try {
            row= run.update(sql,parms);
        } catch (SQLException e) {
            System.err.println("修改失败！");
        }
        return "修改了"+row+"条数据！";
    }
//增加一条数据
    public String addOneDate(String username, String account
        , String password, String email, Integer status,
        Timestamp createtime, Timestamp last_login_time
        ){
    QueryRunner run=new QueryRunner(dataSource);
    String sql="insert into user (username,account,password,email,status,createtime,last_login_time) values(?,?,?,?,?,?,?)";
    Object[] parms={username,account,password,email,status,createtime,last_login_time};
    int row=0;
    try {
        row=run.update(sql,parms);
    } catch (SQLException e) {
        System.err.println("修改失败！");
    }
    return "增加"+row+"条数据！";
}
//软删除
    public String rundelete(Integer id){
    QueryRunner run=new QueryRunner(dataSource);
    String sql="update user set status=2 where id=?";
    Object prams[]={id};
    int rows=0;
        try {
            rows=run.update(sql,prams);
        } catch (SQLException e) {
            System.err.println("获取数据失败!");
        }
        return "删除了"+rows+"数据！";
    }
}
