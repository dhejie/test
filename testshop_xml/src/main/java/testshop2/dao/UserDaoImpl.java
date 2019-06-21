package testshop2.dao;
import lombok.Setter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import testshop2.bean.User;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl {
    @Setter
    private DataSource dataSource;
    public List<User> queryAllUser(){
        QueryRunner run=new QueryRunner(dataSource);
        String sql="select * from user";
        ResultSetHandler<List<User>> rsh=new BeanListHandler<>(User.class);
        List<User> list=new ArrayList<>();
        try {
           list= run.query(sql,rsh);
        } catch (SQLException e) {
            System.out.println("获取数据失败！");
        }
        return list;
    }
    public String addData(String username, String account
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
            System.out.println("修改失败！");
        }
        return "增加"+row+"条数据！";
    }
    public String updata(Integer id,String username, String account
            , String password, String email, Integer status,
                         Timestamp createtime, Timestamp last_login_time){
        QueryRunner run=new QueryRunner(dataSource);
        String sql="update user set username=?,account=?,password=?,email=?,status=?,createtime=?,last_login_time=? where id="+id;
        Object[] parms={username,account,password,email,status,createtime,last_login_time};
        int row=0;
        try {
           row= run.update(sql,parms);
        } catch (SQLException e) {
            System.out.println("修改失败！");
        }
        return "修改了"+row+"条数据！";
    }
    public String deleteData(Integer id){
    QueryRunner run=new QueryRunner(dataSource);
    //Object[] parms={id};
    String sql="delete from user where id="+id;
    int row=0;
        try {
            row=run.update(sql);
        } catch (SQLException e) {
            System.out.println("删除失败！");
        }
        return "删除了"+row+"条数据！";
    }
}
