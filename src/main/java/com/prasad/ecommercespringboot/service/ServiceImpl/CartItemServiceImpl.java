package com.prasad.ecommercespringboot.service.ServiceImpl;

import com.prasad.ecommercespringboot.Exception.CartItemException;
import com.prasad.ecommercespringboot.Exception.UserException;
import com.prasad.ecommercespringboot.model.Cart;
import com.prasad.ecommercespringboot.model.CartItem;
import com.prasad.ecommercespringboot.model.Product;
import com.prasad.ecommercespringboot.model.User;
import com.prasad.ecommercespringboot.repository.CartItemRepository;
import com.prasad.ecommercespringboot.repository.CartRepository;
import com.prasad.ecommercespringboot.service.CartItemService;
import com.prasad.ecommercespringboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {


      private CartItemRepository cartItemRepository;
      private UserService userService;

      private CartRepository cartRepository ;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, UserService userService,
                               CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.cartRepository =cartRepository;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());

         CartItem createdCartItem =cartItemRepository.save(cartItem);

         return  createdCartItem;

    }

    @Override
    public CartItem updateCartItem(long userId, Long id, CartItem cartItem) throws CartItemException, UserException {

          CartItem  item = findCartItemById(id);
          User user = userService.findUserById(item.getUserId());

          if(user.getId().equals(userId))
          {
              item.setQuantity(cartItem.getQuantity());
              item.setPrice(item.getQuantity()*item.getProduct().getPrice());
              item.setDiscountedPrice(item.getQuantity()*item.getProduct().getDiscountedPrice());
              return cartItemRepository.save(item);


          }
          else {
              throw new CartItemException("You can't update  another users cart_item");
          }
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
       CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, size,userId);

       return  cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {

        CartItem cartItem =findCartItemById(cartItemId);

        User user = userService.findUserById(cartItem.getUserId());

         User reqUser = userService.findUserById(userId);

         if(user.getId().equals(reqUser.getId())){
             cartItemRepository.deleteById(cartItemId);
         }
         else{
             throw  new UserException("You can't remove another Users item");
         }
    }

    @Override
    public CartItem findCartItemById(Long CartItemId) throws CartItemException {

        Optional<CartItem> opt = cartItemRepository.findById(CartItemId);
        if(opt.isPresent()){
            return  opt.get();
        }
         throw  new CartItemException("cartItem not found  with id:"+ CartItemId);


    }
}
