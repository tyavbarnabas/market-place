package com.codeafrica.markeplace.service;

import com.codeafrica.markeplace.dto.AddToCartDto;
import com.codeafrica.markeplace.dto.CartDto;
import com.codeafrica.markeplace.dto.CartItemDto;
import com.codeafrica.markeplace.exceptions.CartItemNotExistException;
import com.codeafrica.markeplace.model.Cart;
import com.codeafrica.markeplace.model.Product;
import com.codeafrica.markeplace.model.User;
import com.codeafrica.markeplace.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
        Cart cart = new Cart(product,addToCartDto.getQuantity(),user);
        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {

        List<Cart>cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDto>cartItems = new ArrayList<>();
        for(Cart cart: cartList){
            CartItemDto cartItemDto = new CartItemDto(cart);
            cartItems.add(cartItemDto);
        }

        double totalCost = 0;
        for(CartItemDto cartItemDto: cartItems){
            totalCost += cartItemDto.getProduct().getPrice()*cartItemDto.getQuantity();
        }

        return  new CartDto(cartItems,totalCost);

    }

    public void deleteCartItem(int cartItemId, User user) throws CartItemNotExistException {
        Optional<Cart>optionalCart = cartRepository.findById(cartItemId);
        if(optionalCart.isEmpty()){
            throw new CartItemNotExistException("cartItemId not found");
        }

        Cart cart = optionalCart.get();
        if(cart.getUser()!= user){
            throw new CartItemNotExistException("cart item does not belong to user");
        }

        cartRepository.deleteById(cartItemId);
    }
}
