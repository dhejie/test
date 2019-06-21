package testshop2.annotation_proxy;

import org.springframework.stereotype.Component;

@Component
public class IsayImpl implements Isay {
    @Override
    public void say1(){
        System.out.println("HELLO ,world!");
    }
    @Override
    public void say2(String name){
        System.out.println("Hello"+name);
    }
}
