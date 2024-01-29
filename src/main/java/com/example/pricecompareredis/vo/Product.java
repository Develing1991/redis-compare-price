package com.example.pricecompareredis.vo;

import lombok.Data;

@Data
public class Product {

    private String prodGrpId;  // FPG0001
    private String productId; // UUID
    private double price; // 25000 (WON)
}
