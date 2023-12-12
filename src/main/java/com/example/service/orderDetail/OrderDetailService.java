package com.example.service.orderDetail;

import com.example.Model.*;
import com.example.Model.dto.CartDetailResDTO;
import com.example.repository.ICartDetailRepository;
import com.example.repository.IOrderDetailRepository;
import com.example.service.cartDetail.CartDetailService;
import com.example.service.order.OrderService;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderDetailService {
    @Autowired
    private CartDetailService cartDetailService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    public void create(Cart cart, Customer customer) {
        Order order = new Order();
        order.setSubtotal(cart.getTotalAmount());
        order.setShipping("Free");
        order.setTotalAmount(cart.getTotalAmount());
        order.setStatus(new Status(1L, "Draft"));
        order.setCustomer(customer);
        orderService.create(order);

        List<CartDetail> cartDetailList = cartDetailService.findAllByCart(cart);

        cartDetailList.forEach(cartDetail -> {
            Product product = cartDetail.getProduct();
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setImg(product.getImg());
            orderDetail.setTitle(product.getTitle());
            orderDetail.setQuantity(cartDetail.getQuantity());
            orderDetail.setPrice(cartDetail.getAmount());
            orderDetail.setTotalPrice(cartDetail.getAmount().multiply(new BigDecimal(cartDetail.getQuantity())));
            orderDetail.setProduct(product);
            orderDetailRepository.save(orderDetail);
        });

    }
}

