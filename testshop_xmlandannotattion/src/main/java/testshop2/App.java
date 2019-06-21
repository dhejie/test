package testshop2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import testshop2.test_proxy.spring.UserSerivce2;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserSerivce2 user=ctx.getBean("user", UserSerivce2.class);
        user.addData();
        user.updata();
        user.delete();

    }
}
