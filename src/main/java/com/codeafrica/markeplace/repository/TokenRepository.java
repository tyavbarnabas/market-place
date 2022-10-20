package com.codeafrica.markeplace.repository;

import com.codeafrica.markeplace.model.AuthenticationToken;
import com.codeafrica.markeplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository  extends JpaRepository<AuthenticationToken, Integer>{
    AuthenticationToken findTokenByUser(User user);
    AuthenticationToken findTokenByToken(String token);


}
