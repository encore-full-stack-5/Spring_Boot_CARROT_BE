package com.example.carrot_market.user.service;

import com.example.carrot_market.user.dto.request.SignUpRequestDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void signUp() {
        SignUpRequestDto mock = Mockito.mock(SignUpRequestDto.class);
        Mockito.when(mock.getPhone()).thenReturn("010-1234-5678");
        Mockito.when(mock.getAreaId()).thenReturn(1);
        Mockito.when(mock.getAreaRange()).thenReturn(1);
        Mockito.when(mock.getNickname()).thenReturn("test");
        Mockito.when(mock.getProfileImage()).thenReturn(null);

        userService.signUp(mock);
    }

}