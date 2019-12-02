package servlet;

import bean.User;
import bean.WeiXin;
import bean.users;
import com.alibaba.fastjson.JSONObject;
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

@WebServlet("/callbacks")
public class CallbacksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
//回调
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //调用回调
        WeiXin info = callback(request);
        //获取RegisterServlet的全局变量
        users user = (users) this.getServletContext().getAttribute("user");
        //获取session中的验证码
        JSONObject json = (JSONObject) request.getSession().getAttribute("verifyCode");
        Long createTime = json.getLong("createTime");//发验证码的时间
        long l = System.currentTimeMillis();//当前时间
        //逻辑判断
        if (json == null) {

            response.getWriter().write("验证码错误");

        } else if (!json.getString("mobile").equals(user.getMobile())) {

            response.getWriter().write("手机号错误");

        } else if (!json.getString("verifyCode").equals(user.getVerifyCode())) {

            response.getWriter().write("验证码错误");

        } else if (( l-createTime ) > 1000 * 60 * 5) {

            response.getWriter().write("验证码已过期");

    }else if ("1".equals(user.getKey())){//注册
            //调service层
            UserService service = new UserService();
            //封装
            User user1 = new User();
            user1.setUname(user.getMobile());
            user1.setOpenid(info.getOpenid());
            user1.setNickname(info.getNickname());
            user1.setHeadimg(info.getHeadimgurl());

            try {
                //调用用户判断
                String uname = getUname(user.getMobile());
                //判断是否有该用户
                if(uname.equals("")){
                    //没有该用户，绑定
                    int userInfo = service.updateUserInfo(user1);
                    if(userInfo>0){
                        System.out.println("绑定成功！");
                        System.out.println("登陆成功");
                    }
                }
                response.sendRedirect("/Resold_Apartment/zhan.html");//跳转房源页面
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if ("null".equals(user.getKey())){//登陆
            try {
                //调用户判断
                String uname = getUname(user.getMobile());
                //判断是否有该用户
                if(uname.equals("")){
                    //没有该用户
                    response.sendRedirect("/Resold_Apartment/registered.html");//跳转注册页面
                }else{
                    //存在该用户
                    response.sendRedirect("/Resold_Apartment/zhan.html");//跳转展示页面
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    //判断用户
    public String getUname(String mobile) throws SQLException {
        UserService service = new UserService();

        String uname = service.findUnameByMobile(mobile);
        return uname;
    }
    //回调
    public WeiXin callback(HttpServletRequest request) throws IOException {
        //获取回调
        String code = request.getParameter("code");

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + NetUtils.APPID+
                "&secret=" + NetUtils.APPSECRET+
                "&code=" + code+
                "&grant_type=authorization_code";

        String result = NetUtils.doGetStr(url);

        //转json格式
        ObjectMapper mapper = new ObjectMapper();

        //解析
        Map<String ,Object> map = mapper.readValue(result, new TypeReference<Map<String ,Object>>() {});
        String access_token = (String) map.get("access_token");
        String openid = (String) map.get("openid");

        String url1 = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" +access_token+
                "&openid=" +openid+
                "&lang=zh_CN";

        String result2 = NetUtils.doGetStr(url1);

        //解析
        WeiXin info = mapper.readValue(result2, WeiXin.class);

        return info;
    }
}
