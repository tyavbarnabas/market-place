package com.codeafrica.markeplace.controller;

import com.codeafrica.markeplace.common.ApiResponse;
import com.codeafrica.markeplace.exceptions.AuthenticationFailException;
import com.codeafrica.markeplace.exceptions.OrderNotFoundException;
import com.codeafrica.markeplace.model.Order;
import com.codeafrica.markeplace.model.User;
import com.codeafrica.markeplace.service.AuthenticationServices;
import com.codeafrica.markeplace.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private AuthenticationServices authenticationServices;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse>placeOrder(@RequestParam("token")String token,@RequestParam("sessionId")String sessionId) throws AuthenticationFailException {
        authenticationServices.authenticate(token);
        User user = authenticationServices.getUser(token);
        orderService.placeOrder(user,sessionId);
        return new ResponseEntity<>(new ApiResponse(true,"order has been placed"), HttpStatus.CREATED);

    }


    @GetMapping("/")
    public ResponseEntity<List<Order>>getAllOrders(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationServices.authenticate(token);
        User user = authenticationServices.getUser(token);
        List<Order>orderDtoList = orderService.listOrders(user);
        return  new ResponseEntity<>(orderDtoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object>getOrderById(@PathVariable("id") Integer id,@RequestParam("token")String token) throws AuthenticationFailException, OrderNotFoundException {
        authenticationServices.authenticate(token);
        User user = authenticationServices.getUser(token);
        Order order = orderService.getOrder(id,user);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
}
