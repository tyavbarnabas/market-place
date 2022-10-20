package com.codeafrica.markeplace.service;

import com.codeafrica.markeplace.model.User;
import com.codeafrica.markeplace.model.WishList;
import com.codeafrica.markeplace.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    @Autowired
    private WishListRepository wishListRepository;
    public  void createdWishlist(WishList wishlist){
        wishListRepository.save(wishlist);
    }

    public List<WishList>readWishList(User user){
        return wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
    }
}
