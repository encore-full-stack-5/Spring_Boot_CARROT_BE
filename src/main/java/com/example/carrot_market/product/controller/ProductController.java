package com.example.carrot_market.product.controller;

import com.example.carrot_market.area.domain.model.Area;
import com.example.carrot_market.area.domain.model.AreaRange;
import com.example.carrot_market.area.dto.AddAreaRequestDto;
import com.example.carrot_market.area.service.AreaService;
import com.example.carrot_market.area.service.UpdateUserAreaRequestDto;
import com.example.carrot_market.core.BaseResponseEntity;
import com.example.carrot_market.product.domain.Product;
import com.example.carrot_market.product.dto.InsertProductRequestDto;
import com.example.carrot_market.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<BaseResponseEntity<Product>> addProduct(
            @Valid @RequestPart(value = "product") InsertProductRequestDto dto
//            @RequestPart(value = "images", required = false) MultipartFile[] images
    ) {
        return BaseResponseEntity.ok(productService.insertProduct(dto, null), "상품이 등록되었습니다.");
    }
}