package servlet;

import org.dom4j.DocumentException;
import utils.CheckSignature;
import utils.MessageUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/WeiXinServlet")
public class WeiXinServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            Map<String, String> map = MessageUtils.xml2Map(request);
            //获取接收方和发送方
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");

            String message = null;
            //判断文本还是事件
            if (MessageUtils.MESSAGE_TEXT.equals(msgType)){
                //回复用户的信息

            }else if (MessageUtils.MESSAGE_EVENT.equals(msgType)){
                //判断是什么事件
                String event = map.get("Event");
                if (MessageUtils.MESSAGE_EVENT_SUBSCRIBE.equals(event)){
                    //订阅时回复用户的内容
                    message = MessageUtils.getMessage(fromUserName,toUserName,MessageUtils.subscribe());
                }else if (MessageUtils.MESSAGE_EVENT_CLICK.equalsIgnoreCase(event)) {
                    //点击菜单
                    String eventKey = map.get("EventKey");

                }
            }
            out.print(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //将token，timestamp，nonce三个参数进行字典排序
        String token = "king";
        //调方法
        String signature1 = CheckSignature.checkSignature(token, timestamp, nonce);
        if (signature1.equals(signature)){
            response.getWriter().print(echostr);
        }
    }
}
