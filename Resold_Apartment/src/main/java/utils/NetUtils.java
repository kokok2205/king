package utils;

import bean.AccessToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import menu.Button;
import menu.ClickButton;
import menu.Menu;
import menu.ViewButton;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

//网络请求的类
public class NetUtils {
    public static final String APPID = "wx10a4a20fca849778";
    public static final String APPSECRET = "cc687d7fd0b6e992c09d30ca3467ad0e";
    public static final String BAIDU_APPID="20191115000357218";
    public static final String SECRET_KEY = "swn5R4Vpt1dflrSBweld";
    public static final String  SALT= "1435660288";

    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public static final String GET_MENUINFO_URL = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN";
    public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    public static final String GET_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
    public static final String UPLOADFILE_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    public static final String AUTO_RESPONSE = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=content";
    public static final String CREATE_QRCODE ="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";

    //传路径
    public  static String doGetStr(String urlPath) throws IOException {
        //创建url对象，建立远程连接
        URL url = new URL(urlPath);
        //返回一个连接，连接后台微信服务器
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        //请求方式：开通道get请求只读不写
        urlConnection.setRequestMethod("GET");
        //打开读取流，读取微信服务器返回的数据
        urlConnection.setDoInput(true);
        //关闭输出流，因为是get请求所以关闭输出流，不需要向微信输出
        urlConnection.setDoOutput(false);
        //建立连接打开通道
        urlConnection.connect();
        //读取响应 字节流转字符流高效读取
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
        //读取返回数据
        String len;
        StringBuilder sb = new StringBuilder();
        while ((len=br.readLine())!=null){
            sb.append(len);

        }
        return sb.toString();
    }

    //get传路径
    public  static String doPostStr(String urlPath,String params) throws IOException {
        //获取路径
        URL url = new URL(urlPath);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        //开通道get请求只读不写
        urlConnection.setRequestMethod("GET");
        //打开读取流
        urlConnection.setDoInput(true);
        //post请求，打开输出流因为要想微信服务器传递参数，
        urlConnection.setDoOutput(true);
        //关闭通道缓存
        urlConnection.setUseCaches(false);
        //传参数包装流，向微信服务器传递参数
        PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream());
        //传到微信服务器
        printWriter.write(params);
       //刷新
       printWriter.flush();
        //读取响应 字节流转字符流高效读取
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
        //读取返回效果
        String len;
        StringBuilder sb = new StringBuilder();
        while ((len=br.readLine())!=null){
            sb.append(len);

        }
        return sb.toString();
    }
    //转json格式
    public static AccessToken getAccessToken(){
        //修改信息
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        AccessToken token = new AccessToken();
        try {
            String json = doGetStr(url);
            ObjectMapper mapper = new ObjectMapper();
            token = mapper.readValue(json,AccessToken.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }
//菜单和子菜单
    public static void createMenu(AccessToken accessToken) throws IOException {
        //修改信息
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", getAccessToken().getAccess_token());

        //参数
        //买房
        ViewButton button11 = new ViewButton();
        button11.setType(MessageUtils.MESSAGE_EVENT_VIEW);
        button11.setName("房源");
        String weixin = "http://king.vipgz1.idcfengye.com/Resold_Apartment/LoginServlet";
        button11.setUrl(weixin);
        //子菜单
        ViewButton button12 = new ViewButton();
        button12.setType(MessageUtils.MESSAGE_EVENT_VIEW);
        button12.setName("预约记录");
        button12.setUrl("http://king.vipgz1.idcfengye.com/Resold_Apartment/landing.html");
        //菜单三Sub_button
        Button button1 = new Button();
        button1.setName("卖家");
        //封装
        button1.setSub_button(new Button[]{button11,button12});
        //菜单二view
        ViewButton button2 = new ViewButton();
        button2.setType(MessageUtils.MESSAGE_EVENT_VIEW);
        button2.setName("买家");
        button2.setUrl("https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Creating_Custom-Defined_Menu.html");

        //封装
        Menu menu = new Menu();
        menu.setButton(new Button[]{button1,button2});

        //转json格式
        ObjectMapper mapper = new ObjectMapper();
        String params = mapper.writeValueAsString(menu);
        System.out.println(params);

        //{"errcode":0,"errmsg":"ok"}
        String response = doPostStr(url, params);
        System.out.println(response);
        Map<String ,Object> map = mapper.readValue(response, new TypeReference<Map<String ,Object>>() {});
        System.out.println(map);
        int errcode = -1;
        errcode= (int) map.get("errcode");
        if (errcode==0){
            System.out.println("菜单创建成功");
        }else{
            System.out.println("菜单创建失败");
        }
    }
    //获取菜单信息
    public static String getMenuInfo(AccessToken accessToken) throws IOException {
        //修改信息
        String url = GET_MENUINFO_URL.replace("ACCESS_TOKEN", getAccessToken().getAccess_token());

        String result = doGetStr(url);

        return result;
    }

    //删除菜单
    public static void deleteMenu(AccessToken accessToken) throws IOException {
        //修改信息
        String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", getAccessToken().getAccess_token());

        String result = doGetStr(url);
        //封装
        ObjectMapper mapper = new ObjectMapper();
        //解析
        Map<String ,Object> map = mapper.readValue(result, new TypeReference<Map<String ,Object>>() {});
        int errcode = -1;
        errcode = (int) map.get("errcode");
        if (errcode == 0){
            System.out.println("删除菜单成功");
        }else{
            System.out.println("删除菜单失败");

        }
    }
}
