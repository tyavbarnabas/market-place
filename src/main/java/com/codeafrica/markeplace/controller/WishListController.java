package com.codeafrica.markeplace.controller;

import com.codeafrica.markeplace.common.ApiResponse;
import com.codeafrica.markeplace.dto.ProductDto;
import com.codeafrica.markeplace.exceptions.AuthenticationFailException;
import com.codeafrica.markeplace.model.Product;
import com.codeafrica.markeplace.model.User;
import com.codeafrica.markeplace.model.WishList;
import com.codeafrica.markeplace.repository.ProductRepository;
import com.codeafrica.markeplace.service.AuthenticationServices;
import com.codeafrica.markeplace.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    private WishListService wishListService;
    @Autowired
    AuthenticationServices authenticationServices;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse>addWishList(@RequestBody ProductDto productDto, @RequestParam("token") String token) throws AuthenticationFailException {
        authenticationServices.authenticate(token);
        User user = authenticationServices.getUser(token);

        Product product = productRepository.getReferenceById(productDto.getId());

        WishList wishlist = new WishList(user,product);
        wishListService.createdWishlist(wishlist);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Added to wishlist"), HttpStatus.CREATED);

    }

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>>getWishList(@PathVariable("token") String token) throws AuthenticationFailException {
        authenticationServices.authenticate(token);
        User user = authenticationServices.getUser(token);
        List<WishList> wishLists = wishListService.readWishList(user);


        List<ProductDto>products = new ArrayList<>();
        for (WishList wishList : wishLists ){
            products.add(new ProductDto(wishList.getProduct()));

        }
        return  new ResponseEntity<>(products,HttpStatus.OK);
    }
}
