package com.codeafrica.markeplace.repository;

import com.codeafrica.markeplace.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
