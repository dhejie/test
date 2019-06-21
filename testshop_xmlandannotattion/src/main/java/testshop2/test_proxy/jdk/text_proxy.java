package testshop2.test_proxy.jdk;

public class text_proxy {
    public static void main(String[] args) {
            UserSerivce1Impl1 user1=new UserSerivce1Impl1();
            user1.addUser();
            user1.updateUser();
            user1.deleteUser();
    }
}
