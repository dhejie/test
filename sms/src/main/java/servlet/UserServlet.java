package servlet;

import been.User;
import com.alibaba.fastjson.JSON;
import dao.UserDaoImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    UserDaoImpl userDao = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    boolean tt=judege(req,resp);
    resp.getWriter().println(JSON.toJSONString(tt));
    }

    public boolean judege(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        List<User> list = userDao.queryAllUser();

        for (User li : list
        ) {
            if (li.getAccount() == name) {
                return true;
            } else {
                return false;
            }

        }
        return false;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        resp.setContentType("text/html;charset=UTF-8");
        if(StringUtils.isBlank(action)) {
            resp.getWriter().print("输入有误!");
            return;
        }

        switch (action) {
            case "register":
                registerUser(req, resp);
                break;
            default:
                break;
        }
    }

    private void registerUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //拿到用户填写的注册信息
        User user = new User();
        user.setAccount(req.getParameter("account"));
        user.setUsername(req.getParameter("username"));
        user.setStatus(User.STATUS_NORMAL);
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));


        userDao.inserUser(user);

        // 跳转登录页
        resp.getWriter().print("<script>alert('注册成功!'); window.location.href = 'login.jsp';</script>");
    }
}
