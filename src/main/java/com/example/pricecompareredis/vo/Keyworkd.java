package com.example.pricecompareredis.vo;

import lombok.Data;

import java.util.List;

@Data
public class Keyworkd {
    private String keyword; // 유아용품 - 하기스귀저기(FPG0001), A사 딸랑이 (FPG0002)
    private List<ProductGrp> productGrpList; // [{"FPG0001", [{UUID, 25000}], {"FPG0002", [{}]}...}]
}
