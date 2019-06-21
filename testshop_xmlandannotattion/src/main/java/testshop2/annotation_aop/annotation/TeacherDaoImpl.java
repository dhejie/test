package testshop2.annotation_aop.annotation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Setter
@Getter
@Repository
public class TeacherDaoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional(noRollbackFor = {ArithmeticException.class})
    public List<Teacher> queryAllData(){
      List<Teacher> list=  jdbcTemplate.query("select *from teacher",new BeanPropertyRowMapper<>(Teacher.class));
        return list;
    }
    @Transactional(rollbackFor = {ArithmeticException.class})
    public String insetData(Teacher teacher){
        int row=jdbcTemplate.update("insert into teacher(name) values (?)",teacher.getName());
    return "插入了"+row+"数据";
    }
}
