package com.example.Model;

import com.example.Model.dto.OrderDetailResDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_details")
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Order order;

    private String img;
    private String title;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
    @ManyToOne
    private Product product;

    public OrderDetailResDTO toOrderDetailResDTO() {
        return new OrderDetailResDTO()
                .setImg(img)
                .setTitle(title)
                .setQuantity(quantity)
                .setPrice(price)
                .setTotalPrice(totalPrice);
    }
}

