package com.example.Model;

import com.example.Model.dto.OrderDetailResDTO;
import com.example.Model.dto.OrderResDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal subtotal;
    private String shipping;
    private BigDecimal totalAmount;
    @ManyToOne
    private Status status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<OrderDetail> orderDetailList;

    public OrderResDTO toOrderResDTO() {
        return new OrderResDTO().setSubtotal(subtotal)
                .setShipping(shipping)
                .setTotalAmount(totalAmount)
                .setStatus(status.toString())
                .setOrderDate(orderDate.toString())
                .setCustomer(customer)
                .setOrderDetailResDTOList(orderDetailList.stream()
                        .map(OrderDetail::toOrderDetailResDTO).collect(Collectors.toList()));

    }
}
