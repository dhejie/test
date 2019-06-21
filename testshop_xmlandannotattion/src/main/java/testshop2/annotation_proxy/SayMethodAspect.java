package testshop2.annotation_proxy;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Aspect//表示此类是个切面类
@Component//表示此切面也是一个bean
@Slf4j
public class SayMethodAspect {
    @Around("testshop2.annotation_proxy.GlobalPointcut.sayMethod()")
    public Object testSay(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("大哥来了！");
        Object obj=joinPoint.proceed();
        System.out.println("大哥慢走！");
        log.debug("哈哈哈！哈哈哈！哈哈哈！");
        return obj;
    }
}
