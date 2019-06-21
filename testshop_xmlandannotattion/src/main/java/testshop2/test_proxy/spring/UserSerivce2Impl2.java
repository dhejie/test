package testshop2.test_proxy.spring;

public class UserSerivce2Impl2 implements UserSerivce2 {
    @Override
    public void addData() {
        System.out.println("增加了一条数据！");
    }

    @Override
    public void delete() {
        System.out.println("删除了一条数据！");
    }

    @Override
    public void updata() {
        System.out.println("修改了一条数据！");
    }
}
