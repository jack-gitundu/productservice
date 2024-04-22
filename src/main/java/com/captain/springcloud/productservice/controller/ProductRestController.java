package com.captain.springcloud.productservice.controller;

import com.captain.springcloud.productservice.model.Product;
import com.captain.springcloud.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/product")
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

}
