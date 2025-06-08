package com.example.product_actions.controller;

import com.example.product_actions.model.Product;
import com.example.product_actions.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    @Autowired
    private HttpServletRequest request; // to extract token from headers if needed

    // Helper method to get the JWT token from the request header
    private String getTokenFromRequest() {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    // Helper method to create HttpEntity with JWT header
    private HttpEntity<?> createHttpEntityWithToken(Object body) {
        HttpHeaders headers = new HttpHeaders();
        String token = getTokenFromRequest();
        if (token != null) {
            headers.set("Authorization", "Bearer " + token);
        }
        if (body != null) {
            return new HttpEntity<>(body, headers);
        } else {
            return new HttpEntity<>(headers);
        }
    }

//    @PostMapping("/create")
//    public Product createProduct(@RequestBody Product product) {
//        return productService.createProduct(product);
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteProduct(@RequestParam("id") Long id) {
//        productService.deleteProduct(id);
//        return ResponseEntity.ok("Product deleted successfully");
//    }
//
//    @PatchMapping("/patch")
//    public ResponseEntity<String> updateProduct(@RequestParam ("id") Long id, @RequestBody Product product) {
//        productService.updateProduct(id , product.getProduct_price());
//        return ResponseEntity.ok("Product updated successfully");
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<String> getAllProducts() {
//        String url =  "http://localhost:8082/api/product-view/all";
//        return restTemplate.getForEntity(url , String.class);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<String> getProductById(@PathVariable("id") Long id) {
//        String url =  "http://localhost:8082/api/product-view/product?id="+id;
//        return restTemplate.getForEntity(url , String.class);
//    }
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
    public ResponseEntity<String> updateProduct(@RequestParam("id") Long id, @RequestBody Product product) {
        productService.updateProduct(id, product.getProduct_price());
        return ResponseEntity.ok("Product updated successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllProducts() {
        String url = "http://localhost:8082/api/product-view/all";
        HttpEntity<?> entity = createHttpEntityWithToken(null);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") Long id) {
        String url = "http://localhost:8082/api/product-view/product?id=" + id;
        HttpEntity<?> entity = createHttpEntityWithToken(null);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }
}
