package com.example.carrot_market.product.repository;

import com.example.carrot_market.product.domain.Like;
import com.example.carrot_market.product.domain.Product;
import com.example.carrot_market.product.domain.ProductCategory;
import com.example.carrot_market.product.dto.InsertLikeCountRequestDto;
import com.example.carrot_market.product.dto.UpdateProductRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {
    Optional<Product> selectProductById(int productId);
    void insertProduct(Product product);
    void updateProduct(Product product);
    List<ProductCategory> getProductCategories();
    void updateProductStatus(@Param("id") int id, @Param("state") int state);
    List<Product> findProductsByCategoryAndArea(
            @Param("category") int category,
            @Param("areaId") int areaId,
            @Param("limit") int limit,
            @Param("lastProductId") int lastProductId,
            @Param("distance") int distance
    );
    // 사용자가 등록한 상품 조회
    List<Product> getProductByUserId(int userId, int offset, int limit);
    void insertLikeCount(Like like);

    void updateLikeCountProduct(int id);

    void deleteLikeCount(InsertLikeCountRequestDto req);

    void updateLikeCountProductMinus(int id);

    Optional<Like> selectLike(InsertLikeCountRequestDto req);
  
    // 상품 삭제
    void deleteProduct(Product product);
}
