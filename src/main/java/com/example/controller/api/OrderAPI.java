package com.example.controller.api;

import com.example.Model.Cart;
import com.example.Model.Customer;
import com.example.Model.Order;
import com.example.Model.dto.CartResDTO;
import com.example.Model.dto.OrderResDTO;
import com.example.repository.ICartDetailRepository;
import com.example.service.cart.CartService;
import com.example.service.cartDetail.CartDetailService;
import com.example.service.customer.CustomerService;
import com.example.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private CartDetailService cartDetailService;
    @Autowired
    private ICartDetailRepository cartDetailRepository;
    @GetMapping
    public ResponseEntity<?> showOrder() {
        List<Order> orders = orderService.findAll();
        List<OrderResDTO> orderResDTO = orders.stream().map(Order::toOrderResDTO).collect(Collectors.toList());
        return new ResponseEntity<>(orderResDTO, HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> create() {
        Customer customer = customerService.findById(1L).orElseThrow(() -> new RuntimeException("Cannot found customer"));
        Optional<Cart> cartOptional = cartService.findByCustomer(customer);
        OrderResDTO orderResDTO;
        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cannot found cart");
        }
        else {
            cartResDTO = cartDetailService.update(cartDetailReqDTO, cartOptional.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
