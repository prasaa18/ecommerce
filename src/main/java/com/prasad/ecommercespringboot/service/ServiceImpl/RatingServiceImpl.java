package com.prasad.ecommercespringboot.service.ServiceImpl;

import com.prasad.ecommercespringboot.Exception.ProductException;
import com.prasad.ecommercespringboot.Request.RatingRequest;
import com.prasad.ecommercespringboot.model.Product;
import com.prasad.ecommercespringboot.model.Rating;
import com.prasad.ecommercespringboot.model.User;
import com.prasad.ecommercespringboot.repository.RatingRepository;
import com.prasad.ecommercespringboot.service.ProductService;
import com.prasad.ecommercespringboot.service.RatingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;
    private ProductService productService;

    public RatingServiceImpl(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {

        Product product = productService.findProductById(req.getProductId());

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductRating(Long productid) {


        return ratingRepository.getAllProductsRating(productid);
    }
}
