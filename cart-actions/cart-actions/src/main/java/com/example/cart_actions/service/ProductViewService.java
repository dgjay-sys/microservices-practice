package com.example.cart_actions.service;

import com.example.cart_actions.model.Product;
import com.example.cart_actions.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductViewService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id)  {
        return productRepository.findById(id);
    }
}
