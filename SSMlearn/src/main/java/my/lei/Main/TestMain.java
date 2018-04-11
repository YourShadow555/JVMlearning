package my.lei.Main;

import my.lei.mapper.UserMapper;
import my.lei.pojp.User;
import my.lei.utils.SQLSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(TestMain.class);
        SqlSession sqlSession = null;
        try {
            sqlSession = SQLSessionFactoryUtils.openSqlsession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//            User user = userMapper.getUser("A0001");
//            log.info(user.getUserName());
//            User user=new User();
//            user.setUserId("A0003");
//            user.setUserName("娜美");
//            user.setUserRemark("海贼王的女人");
//            userMapper.deleteUser("A0003");
//            sqlSession.commit();

            List<User> users=userMapper.findUsers("lei");
            for (int i = 0; i < users.size(); i++) {
                System.out.println("name:" + users.get(i).getUserName());
                System.out.println("remark:"+users.get(i).getUserRemark());
            }
        }finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
