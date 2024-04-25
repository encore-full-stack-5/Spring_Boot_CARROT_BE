package com.example.carrot_market.user.db;

import com.example.carrot_market.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> selectUserById(int id);
    Optional<User> selectUserByPhone(String phone);
    void insertUser(User user);
    void updateUser(User user);
    void unRegister(User user);
    Optional<String> getNickname(@Param("userId") int userId);

    double getUserScore(@Param("userId") int userId);
}
