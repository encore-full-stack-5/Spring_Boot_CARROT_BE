package com.example.carrot_market.area.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AddAreaRequestDto {
    @NotEmpty(message = "지역 파라미터가 누락 되었습니다.")
    private int areaId;
    @NotEmpty(message = "유저 아이디 파라미터가 누락 되었습니다.")
    private int userId;
    @NotEmpty(message = "지역 범위 파라미터가 누락 되었습니다.")
    private int areaRange;
    @NotEmpty(message = "기본 지역 설정 파라미터가 누락 되었습니다.")
    private boolean isDefault;
}