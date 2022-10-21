package com.codeafrica.markeplace.controller;


import com.codeafrica.markeplace.common.ApiResponse;
import com.codeafrica.markeplace.dto.AddToCartDto;
import com.codeafrica.markeplace.dto.CartDto;
import com.codeafrica.markeplace.exceptions.AuthenticationFailException;
import com.codeafrica.markeplace.exceptions.CartItemNotExistException;
import com.codeafrica.markeplace.exceptions.ProductNotExistException;
import com.codeafrica.markeplace.model.Product;
import com.codeafrica.markeplace.model.User;
import com.codeafrica.markeplace.service.AuthenticationServices;
import com.codeafrica.markeplace.service.CartService;
import com.codeafrica.markeplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationServices authenticationServices;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token")String token) throws AuthenticationFailException, ProductNotExistException {
        authenticationServices.authenticate(token);
        User user = authenticationServices.getUser(token);
        Product product = productService.getProductById(addToCartDto.getProductId());
        cartService.addToCart(addToCartDto,product,user);

        return new ResponseEntity<>(new ApiResponse(true,"Added to cart"), HttpStatus.CREATED);

    }
    @GetMapping("/")
    public ResponseEntity<CartDto>getCartItems(@RequestParam("token")String token) throws AuthenticationFailException {
        authenticationServices.authenticate(token);
        User user = authenticationServices.getUser(token);
        CartDto cartDto = cartService.listCartItems(user);
        return  new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse>deleteCartItem(@PathVariable("cartItemId")  int cartItemId,
                                                     @RequestParam("token") String token) throws AuthenticationFailException, CartItemNotExistException {
        authenticationServices.authenticate(token);
        User user = authenticationServices.getUser(token);
        cartService.deleteCartItem(cartItemId,user);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"items has been removed"),HttpStatus.OK);
    }



}
