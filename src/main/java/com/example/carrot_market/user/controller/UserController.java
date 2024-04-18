package com.example.carrot_market.user.controller;

import com.example.carrot_market.core.BaseResponseEntity;
import com.example.carrot_market.core.CommonError;
import com.example.carrot_market.core.aop.ExeTimer;
import com.example.carrot_market.user.domain.User;
import com.example.carrot_market.user.domain.UserAggregate;
import com.example.carrot_market.user.dto.request.SignInResponseDto;
import com.example.carrot_market.user.dto.request.SignUpRequestDto;
import com.example.carrot_market.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ExeTimer
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/aop_test")
    public String dbAccess(@RequestParam("test") String test){
        return "2342342";
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
