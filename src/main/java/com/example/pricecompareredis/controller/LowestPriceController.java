package com.example.pricecompareredis.controller;

import com.example.pricecompareredis.service.LowestPriceServiceImpl;
import com.example.pricecompareredis.vo.Product;
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
}
