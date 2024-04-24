package com.prasad.ecommercespringboot.service;



import com.prasad.ecommercespringboot.Exception.UserException;
import com.prasad.ecommercespringboot.model.User;

public interface UserService {

    public User findUserById(Long  id) throws UserException;
    public User findUserProfileByJwt(String jwt) throws UserException ;

}
