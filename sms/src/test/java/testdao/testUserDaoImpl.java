package testdao;

import been.User;
import dao.UserDaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class testUserDaoImpl {
    UserDaoImpl userDao=null;
    @Before
    public void setUp(){
        userDao=new UserDaoImpl();
    }
    @Test
    public void testQueryAllUser(){
        List<User> userList =userDao.queryAllUser();
        Assert.assertEquals("查询结果集一条数据都没有",userList.size());

        Assert.assertNotNull("查询结果不能为空",userList);
    }
}
