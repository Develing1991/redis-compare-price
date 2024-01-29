package com.example.pricecompareredis.service;

import com.example.pricecompareredis.vo.Keyword;
import com.example.pricecompareredis.vo.Product;
import com.example.pricecompareredis.vo.ProductGrp;

import java.util.Set;

public interface LowestPriceService {
    Set getZsetValue(String key);
    Long setNewProduct(Product product);

    Long setNewProductGrp(ProductGrp productGrp);

    Long setNewProductKeyword(String keyword, String productGrpId, double score);

    Keyword getLowestPriceProductByKeyword(String keyword);
}
