package com.example.pricecompareredis.service;

import com.example.pricecompareredis.vo.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LowestPriceServiceImpl implements LowestPriceService{

    private  final RedisTemplate<String, String> myProductPriceRedis;

    @Override
    public Set getZsetValue(String key) {
        //Set myTempSet = new HashSet();
        return myProductPriceRedis.opsForZSet().rangeWithScores(key, 0, 9);
    }

    @Override
    public Long setNewProduct(Product product) {

        myProductPriceRedis.opsForZSet()
                .add(product.getProdGrpId(), product.getProductId(), product.getPrice());

        return myProductPriceRedis.opsForZSet()
                .rank(product.getProdGrpId(), product.getProductId());
    }


}
