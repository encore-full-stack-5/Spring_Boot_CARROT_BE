package com.example.carrot_market.product.controller;

import com.example.carrot_market.area.domain.model.AreaRange;
import com.example.carrot_market.core.base.BaseResponseEntity;
import com.example.carrot_market.product.domain.Product;
import com.example.carrot_market.product.domain.ProductAggregate;
import com.example.carrot_market.product.domain.ProductCategory;
import com.example.carrot_market.product.dto.InsertLikeCountRequestDto;
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
            @Valid @RequestPart(value = "product") InsertProductRequestDto dto,
            @RequestPart(value = "images", required = false) MultipartFile[] images
    ) {
        return BaseResponseEntity.ok(productService.insertProduct(dto, images), "상품이 등록되었습니다.");
    }

    //fetchProducts with cursor paging
    @GetMapping
    public ResponseEntity<BaseResponseEntity<List<ProductAggregate>>> fetchProducts(
            @RequestParam("category") int category,
            @RequestParam("area") int area,
            @RequestParam("limit") int limit,
            @RequestParam("lastProductId") int lastProductId,
            @RequestParam("areaRange") int areaRange
    ) {
        return BaseResponseEntity.ok(
                productService.fetchProducts(category, area, limit, lastProductId, AreaRange.convertIDToAreaRange(areaRange)),
                "success"
        );
    }

    @GetMapping("categories")
    public ResponseEntity<BaseResponseEntity<List<ProductCategory>>> getProductCategories() {
        return BaseResponseEntity.ok(productService.getProductCategories(), "success");
    }

    //update product state
    @PutMapping("{id}/state")
    public ResponseEntity<?> updateProductState(@PathVariable("id") int id, @RequestParam("state") int state) {
        productService.updateProductStatus(id, state);
        return BaseResponseEntity.ok("success");
    }

    // 사용자가 등록한 상품 조회
    @GetMapping("userProducts/{id}")
    public List<Product> getProductsByUserId(
            @PathVariable("id") int userId,
            @RequestParam(value="offset", required=false, defaultValue="0") int offset,
            @RequestParam(value="limit", required=false, defaultValue="5") int limit
    ) {
        return productService.getProductsByUserId(userId, offset, limit);
    }

    @PostMapping("/like_count")
    public void likeProduct(@RequestBody InsertLikeCountRequestDto req) {
        this.productService.likeProduct(req);
    }
    // 상품 삭제
    @PutMapping("/delete/{id}")
    public Product deleteProduct(@PathVariable("id") int productId) {
        return productService.deleteProduct(productId);

    }
}