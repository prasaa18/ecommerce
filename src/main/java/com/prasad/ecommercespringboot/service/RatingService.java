package com.prasad.ecommercespringboot.service;

import com.prasad.ecommercespringboot.Exception.ProductException;
import com.prasad.ecommercespringboot.Request.RatingRequest;
import com.prasad.ecommercespringboot.model.Rating;
import com.prasad.ecommercespringboot.model.User;

import java.util.List;

public interface RatingService {

    public Rating createRating(RatingRequest req, User user) throws ProductException;

    public List<Rating> getProductRating(Long productid);

}
