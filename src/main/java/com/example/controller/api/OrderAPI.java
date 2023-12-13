package com.example.controller.api;

import com.example.Model.Cart;
import com.example.Model.Customer;
import com.example.Model.Order;
import com.example.Model.dto.OrderResDTO;
import com.example.service.cart.CartService;
import com.example.service.customer.CustomerService;
import com.example.service.order.OrderService;
import com.example.service.orderDetail.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/orders")
public class OrderAPI {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<?> showOrder() {
        Customer customer = customerService.findById(1L).orElseThrow(() -> new RuntimeException("Cannot found customer"));

        List<Order> orderList = orderService.findByCustomer(customer);
        List<OrderResDTO> orderResDTO = orderList.stream().map(Order::toOrderResDTO).collect(Collectors.toList());
        return new ResponseEntity<>(orderResDTO, HttpStatus.OK);
    }

    @GetMapping("/checkout")
    public ResponseEntity<?> create() {
        Customer customer = customerService.findById(1L).orElseThrow(() -> new RuntimeException("Cannot found customer"));
        Optional<Cart> cartOptional = cartService.findByCustomer(customer);

        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cannot found cart");
        }
        else {
            orderDetailService.create(cartOptional.get(), customer);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}






