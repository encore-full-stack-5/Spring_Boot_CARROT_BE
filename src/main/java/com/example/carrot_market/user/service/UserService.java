package com.example.carrot_market.user.service;

import com.example.carrot_market.user.domain.User;
import com.example.carrot_market.user.domain.UserAggregate;
import com.example.carrot_market.user.dto.request.SignInResponseDto;
import com.example.carrot_market.user.dto.request.SignUpRequestDto;

public interface UserService {
    UserAggregate singUp(SignUpRequestDto singUpRequestDto);
    UserAggregate singIn(SignInResponseDto signInResponseDto);
}
