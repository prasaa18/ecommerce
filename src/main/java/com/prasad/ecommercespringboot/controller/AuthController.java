package com.prasad.ecommercespringboot.controller;

import com.prasad.ecommercespringboot.Exception.UserException;
import com.prasad.ecommercespringboot.Request.LoginRequest;
import com.prasad.ecommercespringboot.config.JwtProvider;
import com.prasad.ecommercespringboot.model.User;
import com.prasad.ecommercespringboot.repository.UserRepository;
import com.prasad.ecommercespringboot.response.AuthResponse;
import com.prasad.ecommercespringboot.service.ServiceImpl.CustomUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private UserRepository userRepository;

    private JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder;

    private CustomUserServiceImpl customUserService;


      public AuthController(UserRepository userRepository ,CustomUserServiceImpl customUserServiceImpl,
                           PasswordEncoder passwordEncoder, JwtProvider jwtProvider ){
          this.userRepository=userRepository;
          this.customUserService =customUserServiceImpl;
          this.passwordEncoder =passwordEncoder;
          this.jwtProvider =jwtProvider;
      }


      @PostMapping("/signup")
        public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws UserException{
            String email =user.getEmail();
            String password= user.getPassword();
            String firstrString =user.getFirstName();
            String lastString =user.getLastName();

            User isEmailExist =userRepository.findByEmail(email);
            if(isEmailExist!=null){
                throw new UserException("Email is Alreday Used with Another Account");
            }

            User createUser =new User();
            createUser.setEmail(email);
            createUser.setPassword(passwordEncoder.encode(password));
            createUser.setFirstName(firstrString);
            createUser.setLastName(lastString);
            User savedUser= userRepository.save(createUser);

            Authentication authentication =new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtProvider.generateToken(authentication);


            AuthResponse authResponse =new AuthResponse();
            authResponse.setJwt(token);
            authResponse.setMessage("signup Success");

            return  new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);

        }

        @PostMapping("/signin")
        public ResponseEntity<AuthResponse>LoginUserHandler( @RequestBody LoginRequest loginRequest){

          String username= loginRequest.getEmail();
          String password= loginRequest.getPassword();
          Authentication authentication = authenticate(username, password);

          SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtProvider.generateToken(authentication);


            AuthResponse authResponse =new AuthResponse();
            authResponse.setJwt(token);
            authResponse.setMessage("signin Success");
            return  new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
        }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails =customUserService.loadUserByUsername(username);
        if(userDetails ==null)
        {
            throw  new BadCredentialsException("Invalid Uername...");

        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new BadCredentialsException("Invalid Password...");
        }
        return  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
