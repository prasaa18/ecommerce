package com.prasad.ecommercespringboot.service.ServiceImpl;

import com.prasad.ecommercespringboot.Exception.ProductException;
import com.prasad.ecommercespringboot.Request.AddItemRequest;
import com.prasad.ecommercespringboot.model.Cart;
import com.prasad.ecommercespringboot.model.CartItem;
import com.prasad.ecommercespringboot.model.Product;
import com.prasad.ecommercespringboot.model.User;
import com.prasad.ecommercespringboot.repository.CartRepository;
import com.prasad.ecommercespringboot.service.CartItemService;
import com.prasad.ecommercespringboot.service.CartService;
import com.prasad.ecommercespringboot.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    private CartItemService cartItemService;

    private ProductService productService;

    public CartServiceImpl(CartRepository cartRepository, CartItemService cartService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartService;
        this.productService = productService;
    }

    @Override
    public Cart createCart(User user) {
       Cart cart = new Cart();
       cart.setUser(user);
       return  cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {

       Cart cart = cartRepository.findByUserId(userId);
        Product  product =productService.findProductById(req.getProductId());

        CartItem iscartItem  = cartItemService.isCartItemExist(cart,product,req.getSize(),userId);

        if(iscartItem== null){
            CartItem cartItem =new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setUserId(userId);
            cartItem.setQuantity(req.getQuantity());

            int price =req.getQuantity()*product.getDiscountedPrice();

            cartItem.setPrice(price);
            cartItem.setSize(req.getSize());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);

            cart.getCartItems().add(createdCartItem);
        }
        return "Item Added to Cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        int totalPrice =0;
        int totalDiscountedPrice =0;
        int totalItem =0;

        for(CartItem cartItem : cart.getCartItems()){

            totalPrice +=cartItem.getPrice();
            totalDiscountedPrice +=cartItem.getDiscountedPrice();

            totalItem +=cartItem.getQuantity();
        }
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);
        cart.setDiscounte(totalPrice-totalDiscountedPrice);


        return cartRepository.save(cart);
    }



}
