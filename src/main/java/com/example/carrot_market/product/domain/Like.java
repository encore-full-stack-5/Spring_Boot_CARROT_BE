package com.example.carrot_market.product.domain;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Like {
    int id;
    int userId;
    int typeId;
    int type;
    Timestamp createdAt;
}
