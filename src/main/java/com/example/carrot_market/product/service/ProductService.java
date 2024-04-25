package com.example.carrot_market.product.service;

import com.example.carrot_market.area.domain.model.AreaRange;
import com.example.carrot_market.product.domain.Product;
import com.example.carrot_market.product.domain.ProductAggregate;
import com.example.carrot_market.product.domain.ProductCategory;
import com.example.carrot_market.product.domain.ProductResponseDto;
import com.example.carrot_market.product.dto.FetchProductResultDto;
import com.example.carrot_market.product.dto.InsertLikeCountRequestDto;
import com.example.carrot_market.product.dto.InsertProductRequestDto;
import com.example.carrot_market.product.dto.UpdateProductRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

public interface ProductService {

    /**
     * 상품을 생성한다.
     *  status = 1.판매중 2.예약중 3.거래완료 4.업로드중 5.업로드실패
     * @param insertProductRequestDto
     * @return
     */
    Product insertProduct(
            InsertProductRequestDto insertProductRequestDto,
            MultipartFile[] files
    );

    /**
     * 상품을 수정한다. 덮어쓰기 형식
     *
     * @param req
     * @return
     */
    void updateProduct(int id, UpdateProductRequestDto req);


    List<ProductCategory> getProductCategories();
    /**
     * 상품을 끌어올린다..
     *
     * @param productId
     * @return
     */
    void refreshProduct(int productId);

    /**
     * 상품을 조회한다.
     *
     * @param categoryId
     * @param areaId    사용자의 위치 ID
     * @param lastProductId  상품을 불러오는 시작 ID
     * @param limit      상품을 불러오는 갯수
     * @return
     */
    FetchProductResultDto fetchProducts(
            int categoryId,
            int areaId,
            int limit,
            int lastProductId,
            AreaRange areaRange
    );

    /**
     * 사용자가 등록한 상품을 조회한다.
     *
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    List<Product> getProductsByUserId(int userId, int offset, int limit);

    /**
     * 메소드 delete로 상품을 삭제한다.
     * 명시적 삭제 삭제 시점을 deleted_at 컬럼에 기록한다.
     * @param productId
     * @return
     */
    Product deleteProduct(int productId);

    // 상품찜하기
    void likeProduct(InsertLikeCountRequestDto req);

    // 상품찜 취소
    void likeProductCancel(InsertLikeCountRequestDto req);

    // 상품 조회수
    boolean increaseViewCount(int productId);

    void updateProductStatus(int id, int state);
}
