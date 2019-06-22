package servlet;

import been.Message;
import been.User;
import dao.MessageDaoImpl;
import dao.UserDaoImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
@WebServlet ("/LoginServlet")
public class LoginServlet extends HttpServlet {
    UserDaoImpl userDao = null;
    MessageDaoImpl messageDao = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = new UserDaoImpl();
        messageDao = new MessageDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html;charset=UTF-8");
        // 如果用户没有输入用户名或者密码
        if(StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            resp.getWriter().println("<script>alert('用户名和密码不能为空!'); window.location.href = 'login.jsp';</script>");
            return;
        }

        User user = userDao.queryUserByAccount(account);

        // 用户不存在（比如用户名输错了）
        if(user == null) {
            resp.getWriter().println("<script>alert('用户名不存在,请先注册!'); window.location.href = 'login.jsp';</script>");
            return;
        }

        // 密码不对
        if(!password.equals(user.getPassword())) {
            resp.getWriter().println("<script>alert('用户名或密码错误!'); window.location.href = 'login.jsp';</script>");
            return;
        }

        HttpSession session = req.getSession();
        // 保存当前登录用户的完整信息到session中
        session.setAttribute("userInfo", user);
        // 给高频使用的用户昵称单独存储一个key，方便使用
        session.setAttribute("userName", user.getUsername());

        // 更新最后登录时间
        userDao.updateLastLoginTime(user);

        // 查询当前用户收件箱
        List<Message> messageList = messageDao.queryMessageByToId(user.getId());
        req.setAttribute("messageList", messageList);

        // 登录成功，携带消息列表数据跳转到主页（列表页）
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}
