package ex.btvn.controller;

import org.springframework.web.bind.annotation.RestController;

import ex.btvn.model.Product;
import ex.btvn.service.ProductService;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public ArrayList<Product> getMethodName() {
        return productService.getAllProducts();
    }

}
