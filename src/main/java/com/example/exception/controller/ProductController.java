package com.example.exception.controller;

import com.example.exception.exceptionHandler.NoSuchElementFoundException;
import com.example.exception.model.Product;
import com.example.exception.model.ProductInput;
import com.example.exception.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j(topic = "PRODUCT_CONTROLLER")
@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        try {
            return productService.getProduct(id);
        } catch (NoSuchElementFoundException e) {
            e.printStackTrace();
            log.error("error at controller: {}", e.getMessage());
        }

        return null;
    }

    @PostMapping
    public Product addProduct(@RequestBody ProductInput input) {
        return productService.addProduct(input);
    }


}
