package testshop2.test_proxy.cglib;

public class text_cglib {
    public static void main(String[] args) {
        UserSerivceImpl user=new UserSerivceImpl();
        user.addUser();
        user.updateUser();
        user.deleteUser();
    }
}
