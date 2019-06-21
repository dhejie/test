package testshop2.annotation_proxy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(basePackages = "testshop2.annotation_proxy")
@Configuration
@EnableAspectJAutoProxy//开启aop对注解的处理
public class TestAnnotationProxy {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(TestAnnotationProxy.class);
        Isay sa=ctx.getBean(Isay.class);
        sa.say1();
        sa.say2("成小弟");
    }

}
