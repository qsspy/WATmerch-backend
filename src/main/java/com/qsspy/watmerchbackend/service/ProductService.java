package com.qsspy.watmerchbackend.service;

import com.qsspy.watmerchbackend.entity.Category;
import com.qsspy.watmerchbackend.entity.Product;
import com.qsspy.watmerchbackend.repository.CategoryRepository;
import com.qsspy.watmerchbackend.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Category> getCategories() {

        return categoryRepository.findAll();
    }

    @Override
    public Category postCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Page<Product> getProducts(int page, int size, Integer categoryId, Boolean extended, Boolean detailed) {

        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Product> productPage;
        if(categoryId == null) {
            productPage = productRepository.findAll(pageable);
        } else {
            productPage = productRepository.findByCategoryId(categoryId, pageable);
        }
        for(Product product : productPage.getContent()) {
            if(!extended) {
                product.setBasicDetails(null);
            }
            if(!detailed) {
                product.setDetails(null);
            }
        }
        return productPage;
    }

    @Override
    public Product getProduct(String barcode) {
        return productRepository.findById(barcode).get();
    }

    @Override
    public Product postProduct(Product product) {

        return productRepository.save(product);
    }
}
