package been;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Setter
@Getter
public class User {
    //    消息状态： 正常：1； 注销：2；锁定：3
    public static final int STATUS_NORMAL =1;
    public static final int STATUS_DELETE =2;
    public static final int STATUS_LOCKED =3;

    private Integer id;
    private String username;
    private String account;
    private String password;
    private String email;
    private Integer status;
    private Timestamp createtime;
    private Timestamp lastLoginTime;

}
