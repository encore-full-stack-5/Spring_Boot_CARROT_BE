package com.example.carrot_market.user.controller;

import com.example.carrot_market.core.BaseResponseEntity;
import com.example.carrot_market.user.domain.User;
import com.example.carrot_market.user.domain.UserAggregate;
import com.example.carrot_market.user.dto.request.SignInResponseDto;
import com.example.carrot_market.user.dto.request.SignUpRequestDto;
import com.example.carrot_market.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign_up")
    public ResponseEntity<BaseResponseEntity<UserAggregate>> signUp(@Valid @RequestBody SignUpRequestDto signUpRequest) {
        return BaseResponseEntity.ok(userService.singUp(signUpRequest), "success");
    }

    @PostMapping("/sign_in")
    public ResponseEntity<BaseResponseEntity<UserAggregate>> signIn(@Valid @RequestBody SignInResponseDto signInResponseDto) {
        return BaseResponseEntity.ok(userService.singIn(signInResponseDto), "success");
    }
}
