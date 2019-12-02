package servlet;

import bean.WeiXin;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.UserService;
import utils.NetUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/callback")
public class CallbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
//回调
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        CallbacksServlet callbacksServlet = new CallbacksServlet();
        WeiXin userInfo = callbacksServlet.callback(request);
        //将账号与微信绑定，如果有nickname则被绑定，没有则没有被绑定。

        try {
            //调getNickname方法
            String nickName = getNickName(userInfo.getOpenid());
            //判断是否绑定
            if(nickName.equals("")){
                //没有绑定跳登陆页面注册绑定
                request.getRequestDispatcher("/landing.html").forward(request,response);
            }else {
                //绑定好了调房源页面
                request.getRequestDispatcher("/zhan.html").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getNickName(String openId) throws SQLException {
        UserService service = new UserService();

        String nickname = service.findNickNameByOpenIs(openId);

        return nickname;
    }
}
