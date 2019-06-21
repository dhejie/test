package testshop.textmybatis.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
