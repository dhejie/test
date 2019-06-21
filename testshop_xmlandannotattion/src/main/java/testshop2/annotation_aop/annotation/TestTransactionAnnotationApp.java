package testshop2.annotation_aop.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@ComponentScan(basePackages ="testshop2.annotation_aop.annotation")
@EnableTransactionManagement
public class TestTransactionAnnotationApp {
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(TestTransactionAnnotationApp.class);
       query(ctx);
    }
    public static void query(AnnotationConfigApplicationContext ctx){
        TeacherDaoImpl tal= ctx.getBean(TeacherDaoImpl.class);
        List<Teacher> tlist=tal.queryAllData();
        System.out.println(tlist);
    }
    public static void insert(AnnotationConfigApplicationContext ctx){
        TeacherDaoImpl te=ctx.getBean(TeacherDaoImpl.class);
        String str=te.insetData(new Teacher("李老师"));
        System.out.println(str);
    }
}
