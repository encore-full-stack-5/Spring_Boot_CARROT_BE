package com.example.carrot_market.user.db;

import com.example.carrot_market.user.domain.User;

public interface UserRepository {
    User findUserByPhoneNumber(String phoneNumber);
    User CreateUser(User user);
}
