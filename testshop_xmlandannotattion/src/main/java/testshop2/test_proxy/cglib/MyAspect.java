package testshop2.test_proxy.cglib;

public class MyAspect {
    public void before(){
        System.err.println("大哥来了！");
    }
    public void after(){
        System.err.println("大哥慢走！");
    }
}
