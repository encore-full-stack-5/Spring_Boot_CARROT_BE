package com.example.carrot_market.user.dto.request;

import com.example.carrot_market.user.domain.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    public User toDomain() {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        return User.builder()
                .id(0)
                .userScore(36.5)
                .phone(this.getPhone())
                .createdAt(timestamp)
                .nickname(this.getNickname())
                .profileImage(this.getProfileImage())
                .build();
    }
}

