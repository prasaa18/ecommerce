package com.prasad.ecommercespringboot.service;

import com.prasad.ecommercespringboot.Exception.ProductException;
import com.prasad.ecommercespringboot.Request.ReviewRequest;
import com.prasad.ecommercespringboot.model.Review;
import com.prasad.ecommercespringboot.model.User;

import java.util.List;

public interface ReviewService {

    public Review createReview(ReviewRequest req, User user) throws ProductException;

    public List<Review>getAllReview(Long productId);

}
