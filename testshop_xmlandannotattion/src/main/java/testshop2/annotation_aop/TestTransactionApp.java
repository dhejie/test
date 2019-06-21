package testshop2.annotation_aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import testshop2.annotation_aop.bean.Teacher;
import testshop2.annotation_aop.dao.TeacherDaoImpl;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@ImportResource(locations = "classpath:tx_conf.xml")
@ComponentScan(basePackages ="testshop2.annotation_aop")
public class TestTransactionApp {
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    public static void main(String[] args) {
        ApplicationContext ctx=new AnnotationConfigApplicationContext(TestTransactionApp.class);
//queryAll(ctx);
addDate(ctx);
    }
    public static void queryAll(ApplicationContext ctx){
            TeacherDaoImpl tdl=ctx.getBean(TeacherDaoImpl.class);
            List<Teacher> teachers=tdl.querAllDate();
            System.out.println(teachers);
    }
    public static void addDate(ApplicationContext ctx){
        TeacherDaoImpl tdl=  ctx.getBean(TeacherDaoImpl.class);
        String str=tdl.insetTeacher(new Teacher("王老师") );
        System.out.println(str);
    }
}
