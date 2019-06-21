package testshop2.annotation_proxy;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class GlobalPointcut {
    @Pointcut("execution(* testshop2.annotation_proxy..*.*(..))")
    public void sayMethod(){

    }
}
