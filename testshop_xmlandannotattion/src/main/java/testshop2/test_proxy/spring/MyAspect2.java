package testshop2.test_proxy.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import java.util.Arrays;

public class MyAspect2 {
public Object testProxySpring(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    Object obj=joinPoint.proceed();
    long timer =System.currentTimeMillis()-start;
    Signature signature=joinPoint.getSignature();

    String name=signature.getName();
    Object[] args=joinPoint.getArgs();
    System.out.println("方法："+name+"("+ Arrays.toString(args) +")"+"执行"+obj+"用时"+timer);
    return obj;
}
public void beforeMethod(){
    System.out.println("beforeMethod:目标方法开始行动！");
}
public void afterMethod(){
            System.out.println("afterMethod:目标方法over！");
        }

        public void afterReturningMethod(Object obj){
        System.out.println("afterReturningMethod:目标方法over！"+obj);
    }

    public void afterThrowingMethod(Throwable throwable){
        System.out.println("afterReturningMethod:目标方法over！"+throwable);
    }
}
