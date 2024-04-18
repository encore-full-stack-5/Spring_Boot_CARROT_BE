package com.example.carrot_market.product.service;

import com.example.carrot_market.product.domain.Product;
import com.example.carrot_market.product.dto.CreateProductRequestDto;
import com.example.carrot_market.product.dto.UpdateProductRequestDto;

import java.util.List;

public interface ProductService {

    /**
     * 상품을 생성한다.
     *
     * @param createProductRequestDto
     * @return
     */
    Product createProduct(CreateProductRequestDto createProductRequestDto);

    /**
     * 상품을 수정한다. 덮어쓰기 형식
     *
     * @param updateProductRequestDto
     * @return
     */
    Product updateProduct(UpdateProductRequestDto updateProductRequestDto);

    /**
     * 상품을 끌어올린다..
     *
     * @param productId
     * @return
     */
    Product refreshProduct(int productId);

    /**
     * 상품을 조회한다.
     *
     * @param categoryId
     * @param areaId
     * @param offset     상품을 불러오는 시작점 (0부터 시작) 0, 10, 20, 30, 40, 50, ...
     * @param limit      상품을 불러오는 갯수
     * @return
     */
    List<Product> fetchProducts(
            int categoryId,
            int areaId,
            int offset,
            int limit
    );

    /**
     * 사용자가 등록한 상품을 조회한다.
     *
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    List<Product> getProductsByUserId(
            int userId,
            int offset,
            int limit
    );

    /**
     * 메소드 delete로 상품을 삭제한다.
     * 명시적 삭제 삭제 시점을 deleted_at 컬럼에 기록한다.
     * @param productId
     * @return
     */
    boolean deleteProduct(int productId);

    // 상품찜하기
    boolean likeProduct(int productId, int userId);

    // 상품 조회수 올리기
    boolean increaseViewCount(int productId);


}
