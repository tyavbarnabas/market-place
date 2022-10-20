package com.codeafrica.markeplace.repository;

import com.codeafrica.markeplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    Object findByEmail(String email);
}
