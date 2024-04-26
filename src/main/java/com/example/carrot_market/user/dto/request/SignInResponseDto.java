package com.example.carrot_market.user.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;

@Getter
public class SignInResponseDto {
    @NotEmpty(message = "핸드폰 번호 파리미터가 누락 되었습니다.")
    @Pattern(regexp = "^01([0|1|6|7|8|9])-(\\d{3,4})-(\\d{4})$", message = "핸드폰 번호 양식이 잘못되었습니다.")
    private String phone;
}