package com.example.exception.service;

import com.example.exception.exceptionHandler.NoSuchElementFoundException;
import com.example.exception.model.Category;
import com.example.exception.model.I18Constants;
import com.example.exception.model.Product;
import com.example.exception.model.ProductInput;
import com.example.exception.respository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final MessageSource messageSource;

    public Product getProduct(Long id) {
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementFoundException(getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), id)));
    }

    public Product addProduct(ProductInput productInput){
        Product product = new Product();
        product.setName(productInput.getName());
        product.setPrice(productInput.getPrice());
        product.setWeight(product.getWeight());
        product.setCategory(Objects.isNull(productInput.getCategory())? Category.BOOKS: productInput.getCategory());
        return repository.save(product);
    }

    private String getLocalMessage(String key, Long... params){
        return messageSource.getMessage(key,
                params,
                Locale.ENGLISH);
    }
}
