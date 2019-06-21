package testshop2.test_proxy.jdk;

public class UserSerivce1Impl1 implements
        UserSerivce1 {
    @Override
    public void addUser() {

        System.out.println("增加一条数据！");
    }

    @Override
    public void updateUser() {
        System.out.println("修改一条数据！");

    }

    @Override
    public void deleteUser() {
        System.out.println("删除一条数据！");
    }
}
