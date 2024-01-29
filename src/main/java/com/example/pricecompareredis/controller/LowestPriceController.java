package com.example.pricecompareredis.controller;

import com.example.pricecompareredis.service.LowestPriceServiceImpl;
import com.example.pricecompareredis.vo.Keyword;
import com.example.pricecompareredis.vo.Product;
import com.example.pricecompareredis.vo.ProductGrp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class LowestPriceController {

    private final LowestPriceServiceImpl lowestPriceService;

    @GetMapping(path = "/getZsetValue")
    public Set getZsetValue(String key){
        return lowestPriceService.getZsetValue(key);
    }

    @PutMapping(path = "/product")
    public Long setNewProduct(@RequestBody Product product){
        return lowestPriceService.setNewProduct(product);
    }

    @PutMapping(path = "/productGroup")
    public Long setNewProductGrp(@RequestBody ProductGrp productGrp){
        return lowestPriceService.setNewProductGrp(productGrp);
    }

    @PutMapping(path = "/productGroupToKeyword")
    public Long setNewProductKeyword(String keyword, String productGrpId, double score){
        return lowestPriceService.setNewProductKeyword(keyword, productGrpId, score);
    }

    @GetMapping(path = "/productPrice/lowest")
    public Keyword getLowestPriceProductByKeyword(String keyword){
        return lowestPriceService.getLowestPriceProductByKeyword(keyword);
    }
}
