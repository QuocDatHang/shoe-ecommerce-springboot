package com.example.controller.api;

import com.example.Model.dto.CartDetailReqDTO;
import com.example.Model.dto.CartDetailResDTO;
import com.example.Model.dto.CartResDTO;
import com.example.repository.ICartDetailRepository;
import com.example.service.cart.CartService;
import com.example.service.cartDetail.CartDetailService;
import com.example.Model.Cart;
import com.example.Model.Customer;
import com.example.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/carts")
public class CartAPI {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartDetailService cartDetailService;
    @Autowired
    private ICartDetailRepository cartDetailRepository;

    @GetMapping
    public ResponseEntity<?> showCart() {
        Customer customer = customerService.findById(1L).orElseThrow(() -> new RuntimeException("Cannot found customer"));
        Optional<Cart> cartOptional = cartService.findByCustomer(customer);
        if (cartOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CartDetailResDTO> cartDetailResDTOList = cartDetailRepository.getAllCartDetailResDTOByCart(cartOptional.get());

        CartResDTO cartResDTO = new CartResDTO();
        cartResDTO.setCartDetailResDTOList(cartDetailResDTOList);
        cartResDTO.setTotalAmount(cartOptional.get().getTotalAmount());
        return new ResponseEntity<>(cartResDTO, HttpStatus.OK);
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody CartDetailReqDTO cartDetailReqDTO) {
        Customer customer = customerService.findById(1L).orElseThrow(() -> new RuntimeException("Cannot found customer"));
        Optional<Cart> cartOptional = cartService.findByCustomer(customer);

        CartResDTO cartResDTO;
        if (cartOptional.isEmpty()) {
            cartResDTO = cartDetailService.create(cartDetailReqDTO, customer);
        }
        else {
            cartResDTO = cartDetailService.update(cartDetailReqDTO, cartOptional.get());
        }
        return new ResponseEntity<>(cartResDTO, HttpStatus.OK);
    }

    @PostMapping("/minus-product")
    public ResponseEntity<?> increment(@RequestBody CartDetailReqDTO cartDetailReqDTO) {
        Customer customer = customerService.findById(1L).orElseThrow(() -> new RuntimeException("Cannot found customer"));
        Optional<Cart> cartOptional = cartService.findByCustomer(customer);

        CartResDTO cartResDTO;
        if (cartOptional.isEmpty()) {
            throw new RuntimeException("You dont have cart");
        }
        else {
            cartResDTO = cartDetailService.minusProduct(cartDetailReqDTO, cartOptional.get());
        }
        return new ResponseEntity<>(cartResDTO, HttpStatus.OK);
    }

    @PostMapping("/delete-cart-detail")
    public ResponseEntity<?> deleteCartDetail(@RequestBody CartDetailReqDTO cartDetailReqDTO) {
        Customer customer = customerService.findById(1L).orElseThrow(() -> new RuntimeException("Cannot found customer"));
        Optional<Cart> cartOptional = cartService.findByCustomer(customer);

        CartResDTO cartResDTO;
        if (cartOptional.isEmpty()) {
            throw new RuntimeException("You dont have cart");
        }
        else {
            cartResDTO = cartDetailService.delete(cartDetailReqDTO, cartOptional.get());
        }
        return new ResponseEntity<>(cartResDTO, HttpStatus.OK);
    }

    @GetMapping("/delete-cart")
    public ResponseEntity<?> deleteCart() {
        Customer customer = customerService.findById(1L).orElseThrow(() -> new RuntimeException("Cannot found customer"));
        Optional<Cart> cartOptional = cartService.findByCustomer(customer);

        if (cartOptional.isEmpty()) {
            throw new RuntimeException("You dont have cart");
        }
        else {
            cartDetailService.delete(cartOptional.get());
            cartService.delete(cartOptional.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
