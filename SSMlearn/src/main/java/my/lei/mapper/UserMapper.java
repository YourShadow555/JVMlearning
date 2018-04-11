package my.lei.mapper;

import my.lei.pojp.User;

import java.util.List;

public interface UserMapper {
    User getUser(String id);

    int insertUser(User user);

    int deleteUser(String userId);

    int updateUser(User user);

    List<User> findUsers(String userName);
}
