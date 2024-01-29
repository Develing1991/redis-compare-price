package com.example.pricecompareredis.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductGrp {
    private String productGrpId; // FPG0001
    private List<Product> productList; // [{UUID, 25000}, {}... ]
}
