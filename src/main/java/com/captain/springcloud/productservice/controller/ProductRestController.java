package com.captain.springcloud.productservice.controller;

import com.captain.springcloud.productservice.dto.Coupon;
import com.captain.springcloud.productservice.model.Product;
import com.captain.springcloud.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${couponService.url}")
    private String couponServiceURL;

    @PostMapping("/product")
    public Product create(@RequestBody Product product) {

        Coupon coupon = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));

        return productRepository.save(product);
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(null);
    }

}
