package utils;

import bean.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

public class MessageUtils {
    public static final String MESSAGE_TEXT="text";
    public static final String MESSAGE_EVENT="event";
    public static final String MESSAGE_EVENT_SUBSCRIBE="subscribe";
    public static final String MESSAGE_IMAGE="image";
    public static final String MESSAGE_NEWS="news";
    public static final String MESSAGE_MUSIC="music";
    public static final String MESSAGE_EVENT_CLICK="click";
    public static final String MESSAGE_EVENT_VIEW="view";
    public static final String MESSAGE_EVENT_SCANCODE_PUSH="scancode_push";
    public static final String MESSAGE_EVENT_LOCATION_SELECT="location_select";
    public static final String MESSAGE_EVENT_LOCATION="location";
    public static final String MESSAGE_EVENT_SCAN="SCAN";


    //将微信中传过来的xml转成map
    public static Map<String ,String > xml2Map(HttpServletRequest request) throws IOException, DocumentException {
        //创一个键值对
        Map<String ,String> map = new HashMap<>();
        ServletInputStream inputStream = request.getInputStream();
        //创建saxreader对象解析xml
        SAXReader reader = new SAXReader();
        //读取xml文件
        Document document = reader.read(inputStream);
        //获取根节点--xml
        Element root = document.getRootElement();
        //获取根节点里的子节点
        List<Element> elements = root.elements();
        //增强for把名字和内容放到map里
        for (Element element: elements){
            map.put(element.getName(),element.getText());
        }
        return map;
    }
    //转xml文件
    public static String text2XML(TextMessage message){
        XStream xStream = new XStream();
        xStream.alias("xml",TextMessage.class);
        return xStream.toXML(message);
    }
    //订阅时回复用户的内容
    public static String subscribe(){
        StringBuilder sb = new StringBuilder();
        sb.append("欢迎来到滴滴约房！\n");
        return sb.toString();
    }
    //回文本消息
    public static String getMessage(String FromUserName,String ToUserName,String content){
        TextMessage message = new TextMessage();
        //封装
        message.setToUserName(FromUserName);
        message.setFromUserName(ToUserName);
        message.setCreateTime(new Date().getTime()+"");
        message.setMsgType(MESSAGE_TEXT);
        message.setContent(content);

        return text2XML(message);
    }

}
