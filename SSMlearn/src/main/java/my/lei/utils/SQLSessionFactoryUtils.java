package my.lei.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SQLSessionFactoryUtils {
    private final static Class<SQLSessionFactoryUtils> LOCK = SQLSessionFactoryUtils.class;
    private static SqlSessionFactory sqlSessionFactory = null;

    private SQLSessionFactoryUtils() {}

    public static SqlSessionFactory getSqlSessionFactory() {
        synchronized (LOCK) {
            if (sqlSessionFactory != null) {
                return sqlSessionFactory;
            }
            String resource = "mybatis-config.xml";
            InputStream inputStream;
            Properties properties = new Properties();
            try {
                InputStream in = Resources.getResourceAsStream("jdbc.properties");
                properties.load(in);
                String username = properties.getProperty("database.username");
                String password = properties.getProperty("database.password");
                //解密用户名和密码，并且在属性中重置
                properties.put("database.username", DESUtils.getDecryptString(username));
                properties.put("database.password", DESUtils.getDecryptString(password));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,properties);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return sqlSessionFactory;
        }
    }

    public static SqlSession openSqlsession() {
        if (sqlSessionFactory == null) {
            getSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }
}
