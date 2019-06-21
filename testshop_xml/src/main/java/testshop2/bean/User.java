package testshop2.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Setter
@Getter
public class User {
private int id;
private String username;
private String account;
private String password;
private String email;
private int status;
private Timestamp createtime;
private Timestamp last_login_time;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", createtime=" + createtime +
                ", last_login_time=" + last_login_time +
                '}'+"\n";
    }
}
