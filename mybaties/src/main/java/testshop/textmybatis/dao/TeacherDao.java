package testshop.textmybatis.dao;

import org.apache.ibatis.annotations.Select;
import testshop.textmybatis.bean.Teacher;

import java.util.List;

public interface TeacherDao {
    @Select("select * from teacher")
    List<Teacher> teacherQuery();

}
