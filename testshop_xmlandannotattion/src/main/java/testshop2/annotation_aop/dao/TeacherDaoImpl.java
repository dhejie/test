package testshop2.annotation_aop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import testshop2.annotation_aop.bean.Teacher;

import java.util.List;

@Repository
//此注解作用和@Component作用一致含有特定的语义（一般用来标注dao层的类）
public class TeacherDaoImpl {
    @Autowired
private JdbcTemplate jdbcTemplate;

    public List<Teacher> querAllDate(){
        return jdbcTemplate.query("select * from teacher",new BeanPropertyRowMapper<>(Teacher.class));
    }
    public String insetTeacher(Teacher teacher){
        int row=jdbcTemplate.update("insert into teacher(name) values (?)",teacher.getName());
       //创建一个除零的异常
        int a=0/0;
        return "增加了"+row+"数据";
    }
}
