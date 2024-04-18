package com.example.carrot_market.user.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpRequestDto {
    @NotNull(message = "닉네임 파라미터가 누락 되었습니다.")
    @Size(min = 3, max = 10, message = "닉네임 양식이 잘못됬습니다.")
    private String nickname;
    @NotNull(message = "핸드폰 번호 파리미터가 누락 되었습니다.")
    @Pattern(regexp = "^01([0|1|6|7|8|9])-(\\d{3,4})-(\\d{4})$", message = "핸드폰 번호 양식이 잘못되었습니다.")
    private String phone;
    private String profileImage;
    private int areaId;
    private int areaRange;
}

