package com.codeafrica.markeplace.repository;

import com.codeafrica.markeplace.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem,Integer> {

}
