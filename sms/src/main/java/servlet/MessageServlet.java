package servlet;

import been.Message;
import been.User;
import dao.MessageDaoImpl;
import dao.UserDaoImpl;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
    UserDaoImpl userDao = null;
    MessageDaoImpl messageDao = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化
        super.init(config);
        userDao = new UserDaoImpl();
        messageDao = new MessageDaoImpl();
    }
//调用dopost方法
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
   }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //doGet(req,resp);
        String action = req.getParameter("action");
        resp.setContentType("text/html;charset=UTF-8");
        if (StringUtils.isBlank(action)) {
            resp.getWriter().print("参数错误，请检查!");
            return;
        }

        switch (action) {
            case "list":
                queryMessageList(req, resp);
                break;
            case "to_send":
                toSendPage(req, resp);
                break;
            case "send":
                sendMessage(req, resp);

                break;
            case "read":
                readMessage(req, resp);
                break;
            case "reply":
                //回消息
                replyMessage(req, resp);
                break;
            case "delete":
                //删消息
                deleteMessage(req, resp);
                break;
            default:
                break;
        }


    }

//    private void mailMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//       String path=getServletContext().getRealPath("/upload");
//        File filep=new File(path);
//        if(!filep.exists()){
//            filep.mkdir();
//        }
//        System.out.println(path);
//        Part filePart= req.getPart("file");
//        String fileName=filePart.getName();
//        filePart.write(filep+fileName);
//
//
//    }
//删除消息  软删除  更改消息状态
    private void deleteMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id=Integer.parseInt(req.getParameter("id"));
        Message messageid=messageDao.queryMessageById(id);
        messageid.setStatus(Message.STATUS_DELETE);
        messageDao.updateMessage(messageid);
        resp.sendRedirect("MessageServlet?action=list");
    }

//回消息  根据发消息人的id回信
    private void replyMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer messageId = Integer.parseInt(req.getParameter("id"));
        System.out.println(messageId);
        Message message = messageDao.queryMessageById(messageId);

        Integer fromId = message.getFrom_id();

        User user =  userDao.queryUserById(fromId);

        req.setAttribute("fromId", fromId);
        req.setAttribute("user", user);

        req.getRequestDispatcher("reply_message.jsp").forward(req, resp);
    }

//读消息   展示消息类容
    private void readMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer messageId = Integer.parseInt(req.getParameter("id"));
        Message message = messageDao.queryMessageById(messageId);
        User user = userDao.queryUserById(message.getFrom_id());

        // 更新消息状态为已读
        message.setStatus(Message.STATUS_READER);

        // 将更新状态写入数据库
        messageDao.updateMessage(message);





        // 阅读消息也需要显示消息内容和发送人
        req.setAttribute("message", message);
        req.setAttribute("user", user);

        // 跳转到阅读消息页
        req.getRequestDispatcher("read_message.jsp").forward(req, resp);
    }

//发消息
    private void sendMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 获取用户在发送消息页面输入的信息
        String sToId = req.getParameter("to_id");
        String subject = req.getParameter("subject");
        String content = req.getParameter("content");
        // 接收上传的附件
        Part filePath = req.getPart("file");
        // 将文件写到上传目录
        String httpFilePath = writeToUploadPath(req, filePath);
        Integer toId = Integer.parseInt(sToId);
        System.out.println(httpFilePath);
        // 获取发送人信息(其实就是当前登录的用户，登录时我们已经放到session中了)
        User user = (User) req.getSession().getAttribute("userInfo");
        Integer fromId = user.getId();

        Message message = new Message();
        message.setFrom_id(fromId);
        message.setTo_id(toId);
        message.setSubject(subject);
        message.setContent(content);
        message.setStatus(Message.STATUS_UNREAD);
        message.setAttachment(httpFilePath);

        // 发送消息（其实就是存到数据库）
        messageDao.sendMessage(message);

        // 跳转到列表页
        resp.getWriter().println("<script>alert('发送成功');window.location.href = 'MessageServlet?action=list'; </script>");
    }
    //发附件
    private String writeToUploadPath(HttpServletRequest req, Part filePath) throws IOException {
    String upload =getServletContext().getRealPath("/upload/");
        //System.out.println(upload);
    File uploadDir =new File(upload);
    if(!uploadDir.exists()){
        uploadDir.mkdirs();
    }

    String fileName=filePath.getSubmittedFileName();
    String baseName= FilenameUtils.getBaseName(fileName);
    String extName=FilenameUtils.getExtension(fileName);
    StringBuffer
            sb=new StringBuffer();
    sb.append(baseName);
    sb.append("-");
    sb.append(System.currentTimeMillis());
    sb.append("_");
    sb.append(extName);
    fileName=sb.toString();
    File file = new File(upload,fileName);
    //String uploadd=upload+file.getName();
    filePath.write(file.getAbsolutePath());
 //   filePath.write(upload+fileName);
        String basePath = req.getRequestURL().substring(0, req.getRequestURL().lastIndexOf("/"));
        String httpFilePath = basePath + "/upload/" + fileName;
//        System.out.println(basePath);
//        System.out.println(fileName);

    return httpFilePath;
    }

    private void toSendPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = userDao.queryAllUser();
        User user = (User) req.getSession().getAttribute("userInfo");

        // 过滤掉自己
        List<User> userListWithoutMe = new ArrayList<>();
        for (User u : userList) {
            if (u.getId().equals(user.getId())) {
                continue;
            }
            userListWithoutMe.add(u);
        }

        // request作用域传参到发送页面
        req.setAttribute("userList", userListWithoutMe);

        // request跳转到发送页面
        req.getRequestDispatcher("send_message.jsp").forward(req, resp);
    }


    private void queryMessageList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("userInfo");
        List<Message> messageList = messageDao.queryMessageByToId(user.getId());
//        resp.getWriter().println(JSON.toJSONString(messageList));
//        resp.getWriter().close();
        req.setAttribute("messageList", messageList);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}
