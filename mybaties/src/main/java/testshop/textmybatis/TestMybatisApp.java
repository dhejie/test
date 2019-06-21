package testshop.textmybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import testshop.textmybatis.bean.Student;
import testshop.textmybatis.bean.Teacher;
import testshop.textmybatis.dao.StudentDao;
import testshop.textmybatis.dao.TeacherDao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatisApp {
    public static void main(String[] args) throws IOException {
        //读入配置文件
        String confPath = "mybatis.xml";
        InputStream in = Resources.getResourceAsStream(confPath);

        //2，构建SqlSessionFactory(用于获取sqlSession)
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

        //3,获取SqlSession对象（用于具体的CRUD操作）
        SqlSession sqlSession=sqlSessionFactory.openSession();

        //xml配置读取student表中数据
        StudentDao studentDao=sqlSession.getMapper(StudentDao.class);
        List<Student> studentList=studentDao.query();
        System.out.println(studentList);

        //Annotation方式读取teacher表中数据
        TeacherDao teacherDao =sqlSession.getMapper(TeacherDao.class);
        List<Teacher> teacherList=teacherDao.teacherQuery();
        System.out.println(teacherList);
    }
}
