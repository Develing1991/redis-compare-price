package com.example.pricecompareredis.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private String productGrpId;  // FPG0001
    private String productId; // UUID
    private double price; // 25000 (WON)
}
