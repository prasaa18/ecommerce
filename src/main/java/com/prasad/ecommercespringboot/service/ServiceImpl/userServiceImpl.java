package com.prasad.ecommercespringboot.service.ServiceImpl;

import com.prasad.ecommercespringboot.Exception.UserException;
import com.prasad.ecommercespringboot.config.JwtProvider;
import com.prasad.ecommercespringboot.model.User;
import com.prasad.ecommercespringboot.repository.UserRepository;
import com.prasad.ecommercespringboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userServiceImpl implements UserService {


    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    public userServiceImpl(UserRepository userRepository , JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider =jwtProvider;
    }


    @Override
    public User findUserById(Long id) throws UserException {

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return  user.get();

        }
       throw  new UserException("user not Found with id :"+id);


    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
         String email = jwtProvider.getEmailFromJwtToken(jwt);

         User user =userRepository.findByEmail(email);

         if(user == null){
             throw  new UserException("User not found with email"+email);

         }
        return  user;
    }
}
