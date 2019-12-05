package utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JDBCUTLS {
    //连接池
//    private  static BasicDataSource bds=new BasicDataSource();
    private static DruidDataSource bds = new DruidDataSource();
    static{

        try {
            Properties Prop = new Properties();
            InputStream is = JDBCUTLS.class.getClassLoader().getResourceAsStream("db.properties");
            Prop.load(is);
            String driver = Prop.getProperty("jdbc.driverClass");
            String url = Prop.getProperty("jdbc.Url");
            String user = Prop.getProperty("jdbc.user");
            String pwd = Prop.getProperty("jdbc.password");
            //基本配置
            bds.setDriverClassName(driver);
            bds.setUrl(url);
            bds.setUsername(user);
            bds.setPassword(pwd);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public  static DataSource getBDS() {

        return bds;
    }
}
