package dao;

import bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import utils.JDBCUTLS;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    QueryRunner qr = new QueryRunner(JDBCUTLS.getBDS());
//判断有无微信绑定
    public String findNickNameByOpenIs(String openId) throws SQLException {
        String sql = "SELECT nickname FROM user WHERE openid = ?";
        System.out.println(openId);
        List<String> list = qr.query(sql, new ColumnListHandler<String>(), openId);
        //判断有无数据
        if(list.size() == 0){
            return "";
        }else{
            return list.get(0);
        }
    }
    //添加用户和绑定用户号
    public int updateUserInfo(User user) throws SQLException {

        String sql = "INSERT INTO user (uname,openid,nickname,headimg) VALUES (?,?,?,?)";
        Object[] params = {user.getUname(),user.getOpenid(),user.getNickname(),user.getHeadimg()};
        int update = qr.update(sql, params);
        return update;
    }

//判断是否有该用户
    public String findUnameByMobile(String mobile) throws SQLException {
        String sql = "SELECT uname FROM user WHERE uname = ?";
        List<String> list = qr.query(sql, new ColumnListHandler<String>(), mobile);
        //判断有无数据
        if(list.size() == 0){
            return "";
        }else{
            return list.get(0);
        }

    }
}
