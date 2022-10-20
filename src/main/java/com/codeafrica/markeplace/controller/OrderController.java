package com.codeafrica.markeplace.controller;

import com.codeafrica.markeplace.common.ApiResponse;
import com.codeafrica.markeplace.exceptions.AuthenticationFailException;
import com.codeafrica.markeplace.model.User;
import com.codeafrica.markeplace.service.AuthenticationServices;
import com.codeafrica.markeplace.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private AuthenticationServices authenticationServices;

    public ResponseEntity<ApiResponse>placeOrder(@RequestParam("token")String token,@RequestParam("sessionId")String sessionId) throws AuthenticationFailException {
        authenticationServices.authenticate(token);
        User user = authenticationServices.getUser(token);
        orderService.placeOrder(user,sessionId);
        return new ResponseEntity<>(new ApiResponse(true,"order has been placed"), HttpStatus.CREATED);

    }
}
