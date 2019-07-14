package been;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class Message {
//    消息状态： 已读：1； 未读：2；删除：3
    public static final int STATUS_READER =1;
    public static final int STATUS_UNREAD =2;
    public static final int STATUS_DELETE =3;
    private Integer id;
    private Integer from_id;
    private Integer to_id;
    private String subject;
    private String content;
    private Integer status;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp createtime;
    private String attachment;
}
