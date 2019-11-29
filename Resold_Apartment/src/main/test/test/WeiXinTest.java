package test;

import bean.AccessToken;
import org.junit.Test;
import utils.MessageUtils;
import utils.NetUtils;

import java.io.IOException;

public class WeiXinTest {
    @Test
    public void test1(){
        AccessToken token = NetUtils.getAccessToken();
        System.out.println(token.getAccess_token());
        System.out.println(token.getExpires_in());
    }
    @Test
    public void test2() throws IOException {
        NetUtils.createMenu(NetUtils.getAccessToken());

    }
    @Test
    public void test3() throws IOException {

        String menuInfo = NetUtils.getMenuInfo(NetUtils.getAccessToken());
        System.out.println(menuInfo);

    }
    @Test
    public void test4() throws IOException {
        NetUtils.deleteMenu(NetUtils.getAccessToken());
    }

}
