package com.qsspy.watmerchbackend.controller;

import com.qsspy.watmerchbackend.entity.Category;
import com.qsspy.watmerchbackend.entity.Product;
import com.qsspy.watmerchbackend.service.IProductService;
import com.qsspy.watmerchbackend.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private IProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Pobiera listę kategorii
    @GetMapping("/categories")
    public List<Category> getCategories() {

        return productService.getCategories();
    }

    //Zapisuje nową kategorię
    @PostMapping("/categories")
    public Category postCategory(@RequestBody Category category) {
        return productService.postCategory(category);
    }

    //Pobiera listę produktów
    @GetMapping("/products")
    public Page<Product> getProducts(
            @RequestParam(defaultValue = "0") int page, // numer strony 0-indexed
            @RequestParam(defaultValue = "20") int size, // wielkość strony
            @RequestParam(name = "category", required = false) Integer categoryId, //id kategorii
            @RequestParam(defaultValue = "false") Boolean extended,
            @RequestParam(defaultValue = "false") Boolean detailed
    ) {
        return productService.getProducts(page, size, categoryId, extended, detailed);
    }

    //Zapisuje nowy produkt
    @PostMapping("/products")
    public Product postProduct(@RequestBody Product product) {

        return productService.postProduct(product);
    }
}
