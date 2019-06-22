package servlet;

import been.Message;
import been.User;
import com.alibaba.fastjson.JSON;
import dao.MessageDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/updataServlet")
public class updataServlet extends HttpServlet {

    MessageDaoImpl messageDao=new MessageDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        User user = (User) req.getSession().getAttribute("userInfo");
        List<Message> messageList = messageDao.queryMessageByToId(user.getId());
        resp.getWriter().println(JSON.toJSONString(messageList));
        resp.getWriter().close();
    }
}
