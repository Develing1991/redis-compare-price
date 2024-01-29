package com.example.pricecompareredis.service;

import com.example.pricecompareredis.vo.Keyword;
import com.example.pricecompareredis.vo.Product;
import com.example.pricecompareredis.vo.ProductGrp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LowestPriceServiceImpl implements LowestPriceService{

    private  final RedisTemplate<String, String> myProductPriceRedis;
    private final ObjectMapper objectMapper;

    @Override
    public Set getZsetValue(String key) {
        //Set myTempSet = new HashSet();
        return myProductPriceRedis.opsForZSet().rangeWithScores(key, 0, 9);
    }

    @Override
    public Long setNewProduct(Product product) {

        var productUUID = UUID.randomUUID().toString();

        myProductPriceRedis.opsForZSet()
                .add(product.getProductGrpId(), productUUID, product.getPrice());
                // .add(product.getProdGrpId(), product.getProductId(), product.getPrice());

        return myProductPriceRedis.opsForZSet()
                .rank(product.getProductGrpId(), product.getProductId());
    }

    @Override
    public Long setNewProductGrp(ProductGrp productGrp) {
        productGrp.getProductList().forEach(product -> {
            var productUUID = UUID.randomUUID().toString();
            myProductPriceRedis.opsForZSet()
                    .add(productGrp.getProductGrpId(), productUUID, product.getPrice());
                    //.add(productGrp.getProductGrpId(), product.getProductId(), product.getPrice());
        });
        return myProductPriceRedis.opsForZSet().zCard(productGrp.getProductGrpId());
    }

    @Override
    public Long setNewProductKeyword(String keyword, String productGrpId, double score) {
        myProductPriceRedis.opsForZSet().add(keyword, productGrpId, score);
        return myProductPriceRedis.opsForZSet()
                .rank(keyword, productGrpId);
    }

    @Override
    public Keyword getLowestPriceProductByKeyword(String keyword) {
        Keyword keywordInfo = new Keyword();
        List<ProductGrp> productGrpInfo = new ArrayList<>();
        productGrpInfo = getProductGrpUsingKeyword(keyword);
        keywordInfo.setKeyword(keyword);
        keywordInfo.setProductGrpList(productGrpInfo);
        return keywordInfo;
    }

    public List<ProductGrp> getProductGrpUsingKeyword(String keyword){

        List<ProductGrp> productGrpList = new ArrayList<>();

        // elastic search에서 1~2 사이의 값 제공, 많이 매칭 될 수록 2에 가까워짐. ex) 1.12 ... 1.88 ...
        var productGrpIdList
                = List.copyOf(myProductPriceRedis.opsForZSet().reverseRange(keyword, 0, 9)); // 비싼 score 부터 zrevrange


        productGrpIdList.forEach(productGrpId -> {
            var productPriceList
                    = myProductPriceRedis.opsForZSet().rangeWithScores(productGrpId, 0, 9).stream().toList();

            List<Product> productList = new ArrayList<>();

            productPriceList.forEach(productPrice->{
                // [{"value": "UUID"}, {"score": 11000}, ...]
                Map<String, Object> productPriceMap = objectMapper.convertValue(productPrice, Map.class);
                var product = Product.builder()
                                            .productGrpId(productGrpId)
                                            .productId(productPriceMap.get("value").toString())
                                            .price(Double.parseDouble(productPriceMap.get("score").toString()))
                                            .build();
                productList.add(product);
            });

            var productGrp = ProductGrp.builder()
                                            .productGrpId(productGrpId)
                                            .productList(productList)
                                            .build();
            productGrpList.add(productGrp);
        });

        return productGrpList;
    }
}
