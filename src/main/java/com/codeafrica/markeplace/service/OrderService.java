package com.codeafrica.markeplace.service;

import com.codeafrica.markeplace.dto.CartDto;
import com.codeafrica.markeplace.dto.CartItemDto;
import com.codeafrica.markeplace.exceptions.OrderNotFoundException;
import com.codeafrica.markeplace.model.Order;
import com.codeafrica.markeplace.model.OrderItem;
import com.codeafrica.markeplace.model.User;
import com.codeafrica.markeplace.repository.OrderItemsRepository;
import com.codeafrica.markeplace.repository.OrderRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class OrderService {
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    public void placeOrder(User user, String sessionId) {
        CartDto cartDto = cartService.listCartItems(user);
        List<CartItemDto>cartItemDtoList = cartDto.getCartItems();

        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date());
        newOrder.setSessionId(sessionId);
        newOrder.setUser(user);
        newOrder.setTotalPrice(cartDto.getTotalCost());
        orderRepository.save(newOrder);


        for(CartItemDto cartItemDto: cartItemDtoList){
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(cartItemDto.getProduct().getPrice());
            orderItem.setProduct(cartItemDto.getProduct());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setOrder(newOrder);
            orderItemsRepository.save(orderItem);


        }


    }

    public List<Order> listOrders(User user) {
        return orderRepository.findAllByUserOrderByCreatedDateDesc(user);
    }

    public Order getOrder(Integer orderId, User user) throws OrderNotFoundException {
        Optional<Order>optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()){
            throw new OrderNotFoundException("order with the id is not valid");
        }

        Order order = optionalOrder.get();
        if(order.getUser() != user){
            throw  new OrderNotFoundException("order does not exist");
        }

        return order;
    }
}
