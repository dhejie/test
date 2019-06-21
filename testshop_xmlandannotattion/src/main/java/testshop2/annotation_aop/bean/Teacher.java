package testshop2.annotation_aop.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Teacher {
    private String id;
    private String name;

    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public Teacher( String name) {
        this.name = name;
    }
    public Teacher(){}
}
