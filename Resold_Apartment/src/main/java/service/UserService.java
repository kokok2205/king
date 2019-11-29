package service;

import bean.User;
import dao.UserDao;

import java.sql.SQLException;

public class UserService {
    UserDao dao = new UserDao();

    public int updateUserInfo(User user1) throws SQLException {

        return dao.updateUserInfo(user1);
    }

    public String findNickNameByOpenIs(String openId) throws SQLException {

        return dao.findNickNameByOpenIs(openId);
    }

    public String findUnameByMobile(String mobile) throws SQLException {

        return dao.findUnameByMobile(mobile);
    }
}
