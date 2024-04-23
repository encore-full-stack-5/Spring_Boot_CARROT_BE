package com.example.carrot_market.area.service;

import com.example.carrot_market.area.domain.model.UserArea;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UpdateUserAreaRequestDto {
    private int userId;
    private int areaId;
    private int areaRange;
    private int isDefault;
    private int orgAreaId;
    private int otherAreaId;
}
