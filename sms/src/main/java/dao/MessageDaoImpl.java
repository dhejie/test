package dao;

import been.Message;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCTools;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//根据消息id查询消息  返回一个Message对象
public class MessageDaoImpl {
    public Message queryMessageById(Integer id){
        DataSource ds=JDBCTools.getDataSource();
        QueryRunner rn=new QueryRunner(ds);
        Message res=null;
        try {
            res=rn.query("select * from message where id = ?",
                    new BeanHandler<Message>(Message.class),new Object[]{id});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

//根据接接受人查询消息  接收人的id  to_id
    public List<Message> queryMessageByToId(Integer toId){
        DataSource ds = JDBCTools.getDataSource();
        QueryRunner runner = new QueryRunner(ds);
        List<Message> result = new ArrayList<>();
        try {
            result = runner.query("select * from message where to_id = ? and status!=?",
                    new BeanListHandler<Message>(Message.class), new Object[]{toId,Message.STATUS_DELETE});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    //发送消息   就是在Message增加一条数据
    public Object sendMessage(Message message) {
        DataSource ds = JDBCTools.getDataSource();
        QueryRunner runner = new QueryRunner(ds);
        String sql = "insert into message (from_id, to_id, " +
                "subject, content, createtime, status, " +
                "attachment) values (?,?,?,?,?,?,?)";

        Object[] params = new Object[]{
                message.getFrom_id(),
                message.getTo_id(),
                message.getSubject(),
                message.getContent(),
                new Timestamp(System.currentTimeMillis()),
                message.getStatus(),
                message.getAttachment()
        };
        ArrayHandler handler = new ArrayHandler();
        try {
            Object[] retArr = runner.insert(sql, handler, params);
            return  retArr[0];
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //更新消息  目的更新最后登陆时间
    public Object updateMessage(Message message) {
        DataSource ds = JDBCTools.getDataSource();
        QueryRunner runner = new QueryRunner(ds);
        String sql = "update message set from_id = ?, to_id = ?, " +
                "subject = ?, content = ?, createtime = ?, status = ?, " +
                "attachment = ? where id = ?";

        Object[] params = new Object[]{
                message.getFrom_id(),
                message.getTo_id(),
                message.getSubject(),
                message.getContent(),
                new Timestamp(System.currentTimeMillis()),
                message.getStatus(),
                message.getAttachment(),
                message.getId()
        };

        try {
            return runner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
