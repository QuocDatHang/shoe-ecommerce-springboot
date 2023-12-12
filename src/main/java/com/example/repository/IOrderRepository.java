package com.example.repository;

import com.example.Model.Customer;
import com.example.Model.Order;
import com.example.Model.Status;
import com.example.Model.dto.OrderDetailResDTO;
import com.example.Model.dto.OrderResDTO;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {
//    @Query("SELECT new com.example.Model.dto.OrderResDTO( " +
//            "o.subtotal," +
//            "o.shipping," +
//            "o.totalAmount," +
//            "o.status," +
//            "o.orderDate," +
//            "o.customer," +
//            "o.orderDetailList )" +
//            "FROM Order AS o WHERE o.customer = :customer")
//    List<OrderResDTO> findAllByCustomer(Customer customer);

    List<Order> findByCustomer(Customer customer);
}
