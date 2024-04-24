package com.prasad.ecommercespringboot.service;

import com.prasad.ecommercespringboot.Exception.ProductException;
import com.prasad.ecommercespringboot.Request.CreateProductRequest;
import com.prasad.ecommercespringboot.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService{
    public Product createProduct(CreateProductRequest createProductRequest);

    public String deleteProduct(Long productId) throws ProductException;

    public Product updateProduct(Long productId, Product product) throws  ProductException;

    public Product findProductById(long id) throws  ProductException;

    public
    List<Product> findProductByCategory(String category);

    public Page<Product> getAllProduct(String category , List <String> color, List<String> sizes, Integer minPrice,
                                       Integer maxPrice, Integer minDiscount , String sort, String stock, Integer pageNumber, Integer PageSize);

}
