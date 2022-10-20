package com.codeafrica.markeplace.controller;


import com.codeafrica.markeplace.dto.SignInDto;
import com.codeafrica.markeplace.dto.SignInResponseDto;
import com.codeafrica.markeplace.dto.SignUpResponseDto;
import com.codeafrica.markeplace.dto.SignUpDto;
import com.codeafrica.markeplace.exceptions.AuthenticationFailException;
import com.codeafrica.markeplace.exceptions.CustomException;
import com.codeafrica.markeplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public SignUpResponseDto signup(@RequestBody SignUpDto signUpDto) throws CustomException{
        return userService.signUp(signUpDto);

    }
    @PostMapping("/signIn")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) throws CustomException, AuthenticationFailException {
        return  userService.signIn(signInDto);
    }




}
