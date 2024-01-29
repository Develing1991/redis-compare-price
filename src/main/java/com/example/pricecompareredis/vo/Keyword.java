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
public class Keyword {
    private String keyword; // 유아용품 - 하기스귀저기(FPG0001), A사 딸랑이 (FPG0002)
    private List<ProductGrp> productGrpList; // [{"FPG0001", [{UUID, 25000}], {"FPG0001", [{}]}...}]
}
