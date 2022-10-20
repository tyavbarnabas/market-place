package com.codeafrica.markeplace.repository;

import com.codeafrica.markeplace.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {


}
