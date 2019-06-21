package testshop2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import testshop2.bean.User;
import testshop2.dao.UserDaoImpl;

import java.sql.Timestamp;
import java.util.List;

/**
 * Hello world!
 *
 */
@Configuration//相当于一个xml配置文件
@Import(MyDataSource.class)//导入其他类的注解配置
@ComponentScan( "testshop2")//扫描路径
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(App.class);
    ctx.registerShutdownHook();
//          query(ctx);
//           insert(ctx);
//           update(ctx);
//           delete(ctx);
    }
    //查询全部数据
    public static void query(AnnotationConfigApplicationContext ctx){
        UserDaoImpl user=ctx.getBean(UserDaoImpl.class);
        List<User> userList= user.queryAllDate();
        System.out.println(userList);
    }
    //插入一条数据
    public static void insert(AnnotationConfigApplicationContext ctx){
        UserDaoImpl user=ctx.getBean(UserDaoImpl.class);
       String str= user.addOneDate("","","","",1,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
        System.out.println(str);
    }
    //修改一条数据
    public static void update(AnnotationConfigApplicationContext ctx){
        UserDaoImpl user=ctx.getBean(UserDaoImpl.class);
        String str= user.updateOneDate(3,"","","","",1,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
        System.out.println(str);
    }
    //真删除一数据
    public static void delete(AnnotationConfigApplicationContext ctx){
        UserDaoImpl user=ctx.getBean(UserDaoImpl.class);
        String str=user.deleteOneDate(4);
        System.out.println(str);
    }
}
