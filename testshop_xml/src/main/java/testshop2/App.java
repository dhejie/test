package testshop2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import testshop2.bean.User;
import testshop2.dao.UserDaoImpl;

import java.sql.Timestamp;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
       //查询user所有数据
        //queryAll(ctx);
        //删除一条数据
        //deleteOneData(ctx);
        //增加一条数据
        //addOneData(ctx);
        //updata(ctx);
    }
    public static void queryAll(ApplicationContext ctx){
        UserDaoImpl user=ctx.getBean(UserDaoImpl.class);
        List<User> ulist= user.queryAllUser();
        System.out.println(ulist);
    }

    public static void deleteOneData(ApplicationContext ctx){
    UserDaoImpl user=ctx.getBean(UserDaoImpl.class);
    user.deleteData(4);
    }
    public static void addOneData(ApplicationContext ctx){
        UserDaoImpl user=ctx.getBean(UserDaoImpl.class);
        String str= user.addData("","","","",1,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
        System.out.println(str);
    }
    public static void updata(ApplicationContext ctx){
        UserDaoImpl user=ctx.getBean(UserDaoImpl.class);
        String str= user.updata(3,"","","","",1,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
        System.out.println(str);
    }


}
