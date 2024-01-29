package com.example.pricecompareredis.vo;

import lombok.Data;

@Data
public class Product {
    private String productId; // UUID
    private int price; // 25000 (WON)
}
