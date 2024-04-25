package com.example.carrot_market.chatting.domain;

import com.example.carrot_market.area.domain.model.Area;
import com.example.carrot_market.product.domain.Product;
import com.example.carrot_market.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
public class ChatRoomAggregate {
    private int id;

    private int productId;
    private int sellerId;
    private int customerId;
    private Timestamp create_at;
    private int seller_last_read_chat_id;
    private int customer_last_read_chat_id;
    private int is_exist_seller;
    private int is_exist_customer;
    private String lastChat;
    // 유저
    private int partnerId;
    private String nickname;
    private String profileImage;
    // 상품
    private String productImage;
    private String productTitle;
    private int productPrice;
    private int productState;
    // 지역
    private String address;
}
