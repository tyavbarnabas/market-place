package com.codeafrica.markeplace.repository;

import com.codeafrica.markeplace.model.User;
import com.codeafrica.markeplace.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WishListRepository extends JpaRepository<WishList,Integer> {
    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);

}
