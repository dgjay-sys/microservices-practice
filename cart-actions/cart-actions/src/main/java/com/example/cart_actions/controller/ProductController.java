package com.example.cart_actions.controller;

import com.example.cart_actions.model.Product;
import com.example.cart_actions.service.ProductViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/product-view")
public class ProductController {
    @Autowired
    private ProductViewService productService;


    @GetMapping("/all")
    public List<Product> getAllProducts()
    {
        return productService.getProducts();
    }

    @GetMapping("/product")
    public Optional<Product> getProductById(@RequestParam("id") Long id){
        return productService.getProductById(id);
    }
}
