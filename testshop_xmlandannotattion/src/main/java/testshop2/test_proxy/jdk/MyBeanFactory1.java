package testshop2.test_proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanFactory1 {
    public static UserSerivce1 createService(){
       //目标类
        final UserSerivce1 userSerivce1 =new UserSerivce1Impl1();
        //切面类
        final MyAspect1 myAspect1 =new MyAspect1();
        UserSerivce1 proxySerivce =(UserSerivce1) Proxy.newProxyInstance(
                MyBeanFactory1.class.getClassLoader(),
                userSerivce1.getClass().getInterfaces(),
               new InvocationHandler(){
                   @Override
                   public Object invoke(Object proxy, Method method,
                                        Object[] args) throws Throwable {
/*
* Object jdk :代理对象
* Method method:代理对象当前执行的描述方法（对象）
*Object[] args:方法实际参数
*
* */
                       myAspect1.before();
                      Object obj= method.invoke(userSerivce1,args );
                       myAspect1.after();
                       return obj;
                   }
               });
        return proxySerivce;
    }
}
