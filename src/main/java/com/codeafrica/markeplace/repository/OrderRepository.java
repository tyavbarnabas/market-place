package com.codeafrica.markeplace.repository;

import com.codeafrica.markeplace.model.Order;
import com.codeafrica.markeplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAllByUserOrderByCreatedDateDesc(User user);

}
