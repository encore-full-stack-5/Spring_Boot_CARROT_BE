package com.example.carrot_market.product.domain;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class ProductImage {
    private int id;           // Corresponds to the 'id' field in the database
    private String file_path;  // Corresponds to the 'file_path' field in the database
    private int type;       // Corresponds to the 'type' field in the database, e.g., "image"
    private int type_id;      // Corresponds to the 'type_id' field in the database, for the product type ID
    private Timestamp created_at;  // Corresponds to the 'created_at' field in the database
}