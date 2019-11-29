package servlet;

import utils.NetUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
//登陆
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String callBack = "http://king.vipgz1.idcfengye.com/Resold_Apartment/callback";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + NetUtils.APPID+
                "&redirect_uri=" +callBack+
                "&response_type=code" +
                "&scope=" +"snsapi_userinfo"+
                "&state=STATE#wechat_redirect";

        response.sendRedirect(url);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
