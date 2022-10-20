package com.codeafrica.markeplace.service;


import com.codeafrica.markeplace.config.MessageStrings;
import com.codeafrica.markeplace.dto.SignInDto;
import com.codeafrica.markeplace.dto.SignInResponseDto;
import com.codeafrica.markeplace.dto.SignUpResponseDto;
import com.codeafrica.markeplace.dto.SignUpDto;
import com.codeafrica.markeplace.exceptions.AuthenticationFailException;
import com.codeafrica.markeplace.exceptions.CustomException;
import com.codeafrica.markeplace.model.AuthenticationToken;
import com.codeafrica.markeplace.model.User;
import com.codeafrica.markeplace.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationServices authenticationServices;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public SignUpResponseDto signUp(SignUpDto signUpDto) throws CustomException {
        if(Objects.nonNull(userRepository.findByEmail(signUpDto.getEmail()))){
            throw new CustomException("user Already Exist");
        }
        String encryptedPassword = signUpDto.getPassword();
        try{
           encryptedPassword  = hashPassword(signUpDto.getPassword());
        }catch ( NoSuchAlgorithmException e){
            e.printStackTrace();

            logger.error("hashing password failed {}",e.getMessage());;

        }

        User user =new User(signUpDto.getFirstName(),signUpDto.getLastName(),signUpDto.getEmail(),encryptedPassword);
        try {
            userRepository.save(user);
            final AuthenticationToken authenticationToken = new AuthenticationToken(user);
            authenticationServices.saveConfirmationToken(authenticationToken);

            return new SignUpResponseDto("success","user created successfully");
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }

    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;

    }

    public SignInResponseDto signIn(SignInDto signInDto) throws AuthenticationFailException, CustomException {
        User user = (User) userRepository.findByEmail(signInDto.getEmail());
        if(!Objects.nonNull(user)){
            throw new AuthenticationFailException("user not present");
        }try {
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthenticationFailException(MessageStrings.WRONG_PASSWORD);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed{}",e.getMessage());
            throw new RuntimeException(e);
        }
        AuthenticationToken token = authenticationServices.getToken(user);

        if(!Objects.nonNull(token)){
            throw new CustomException(MessageStrings.WRONG_PASSWORD);
        }
        return  new SignInResponseDto("success", token.getToken());
    }
}
