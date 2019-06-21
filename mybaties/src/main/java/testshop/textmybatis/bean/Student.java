package testshop.textmybatis.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Student {
    private int id;
    private String sname;
private int age;
private String gender;
private String nick_name;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", nick_name='" + nick_name + '\'' +
                '}';
    }
}
