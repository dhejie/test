package testshop2.test_proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyBeanFactory {
    public static void createService() {
        //目标类
        final UserSerivceImpl userSerivce = new UserSerivceImpl();
        //切面类
        final MyAspect myAspect = new MyAspect();
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(UserSerivceImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
               myAspect.before();
                Object obj= method.invoke(userSerivce, objects);
                myAspect.after();
                return null;
            }
        });
        UserSerivceImpl proxySerivce = (UserSerivceImpl) enhancer.create();
    }
}
