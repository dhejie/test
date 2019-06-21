package testshop2.annotation_aop.annotation;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Teacher {
    private String id;
    private String name;

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Teacher(String name) {
        this.name = name;
    }
}
