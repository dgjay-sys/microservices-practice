package com.example.product_actions.controller;

import com.example.product_actions.model.Product;
import com.example.product_actions.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @PatchMapping("/patch")
    public ResponseEntity<String> updateProduct(@RequestParam ("id") Long id, @RequestBody Product product) {
        productService.updateProduct(id , product.getProduct_price());
        return ResponseEntity.ok("Product updated successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllProducts() {
        String url =  "http://localhost:8082/api/product-view/all";
        return restTemplate.getForEntity(url , String.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") Long id) {
        String url =  "http://localhost:8082/api/product-view/product?id="+id;
        return restTemplate.getForEntity(url , String.class);
    }
}
