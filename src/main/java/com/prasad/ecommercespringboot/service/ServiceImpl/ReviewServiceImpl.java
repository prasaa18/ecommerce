package com.prasad.ecommercespringboot.service.ServiceImpl;

import com.prasad.ecommercespringboot.Exception.ProductException;
import com.prasad.ecommercespringboot.Request.ReviewRequest;
import com.prasad.ecommercespringboot.model.Product;
import com.prasad.ecommercespringboot.model.Review;
import com.prasad.ecommercespringboot.model.User;
import com.prasad.ecommercespringboot.repository.ProductRepository;
import com.prasad.ecommercespringboot.repository.ReviewRepository;
import com.prasad.ecommercespringboot.service.ProductService;
import com.prasad.ecommercespringboot.service.ReviewService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private ProductService productService;

    private ProductRepository productRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductService productService, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());

        Review review =new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setCreatedAt(LocalDateTime.now());
        review.setReview(req.getReview());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
