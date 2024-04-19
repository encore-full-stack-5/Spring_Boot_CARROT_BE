package com.example.carrot_market.product.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public record InsertProductRequestDto (

        @NotNull(message = "userId 파라미터가 누락 되었습니다.")
        int userId,
        @NotNull(message = "areaId 파라미터가 누락 되었습니다.")
        int areaId,
        @NotNull(message = "categoryId 파라미터가 누락 되었습니다.")
        int categoryId,
        @NotNull(message = "title 파라미터가 누락 되었습니다.")
        String title,
        @NotNull(message = "content 파라미터가 누락 되었습니다.")
        String content,
        @NotNull(message = "price 파라미터가 누락 되었습니다.")
        int price,
        @NotNull(message = "isNegotiation 파라미터가 누락 되었습니다.")
        int isNegotiation
) {
}
