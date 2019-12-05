package servlet;

import bean.User;
import bean.users;
import com.alibaba.fastjson.JSONObject;
import service.UserService;
import utils.NetUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 注册
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;


  public RegisterServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html;charset=utf-8");
    request.setCharacterEncoding("utf-8");
    //回调路径
    String callBack = "http://king.vipgz1.idcfengye.com/Resold_Apartment/callbacks";
    String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
            "appid=" + NetUtils.APPID+
            "&redirect_uri=" +callBack+
            "&response_type=code" +
            "&scope=" +"snsapi_userinfo"+
            "&state=STATE#wechat_redirect";
    //重定向
    response.sendRedirect(url);


    String mobile = request.getParameter("mobile");//获取手机号
    String verifyCode = request.getParameter("verifyCode");//获取验证码
    String key = request.getParameter("key");//判断是注册还是登陆有参则为注册

    //用户信息封装
    users user = new users();
    user.setKey(key);
    user.setMobile(mobile);
    user.setVerifyCode(verifyCode);

    //存全局变量
    this.getServletContext().setAttribute("user",user);
  }
}
