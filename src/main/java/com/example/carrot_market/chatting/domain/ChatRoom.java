package com.example.carrot_market.chatting.domain;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class ChatRoom {
    //id, product_id, seller_id, customer_id, create_at, seller_last_read_chat_id, customer_last_read_chat_id, is_exist_seller, is_exist_customer
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
}
