package com.example.carrot_market.product.service;

import com.example.carrot_market.area.domain.model.AreaRange;
import com.example.carrot_market.product.domain.Product;
import com.example.carrot_market.product.domain.ProductAggregate;
import com.example.carrot_market.product.domain.ProductCategory;
import com.example.carrot_market.product.dto.FetchProductResult;
import com.example.carrot_market.product.dto.InsertLikeCountRequestDto;
import com.example.carrot_market.product.dto.InsertProductRequestDto;
import com.example.carrot_market.product.dto.UpdateProductRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {


    Product insertProduct(
            InsertProductRequestDto insertProductRequestDto,
            MultipartFile[] files
    );

    void updateProduct(int id, UpdateProductRequestDto req);

    List<ProductCategory> getProductCategories();

    void refreshProduct(int productId);

    FetchProductResult fetchProducts(
            int categoryId,
            int areaId,
            int limit,
            int lastProductId,
            AreaRange areaRange
    );

    List<Product> getProductsByUserId(int userId, int offset, int limit);

    Product deleteProduct(int productId);

    // 상품찜하기
    void likeProduct(InsertLikeCountRequestDto req);

    // 상품찜 취소
    void likeProductCancel(InsertLikeCountRequestDto req);

    // 상품 조회수
    boolean increaseViewCount(int productId);

    void updateProductStatus(int id, int state);

    ProductAggregate selectProductById(int productId);


}
