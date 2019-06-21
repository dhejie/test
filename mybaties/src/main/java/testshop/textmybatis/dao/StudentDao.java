package testshop.textmybatis.dao;

import testshop.textmybatis.bean.Student;

import java.util.List;

public interface StudentDao {
    List<Student> query();
}
