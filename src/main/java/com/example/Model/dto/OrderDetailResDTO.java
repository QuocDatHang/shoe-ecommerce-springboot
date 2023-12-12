package com.example.Model.dto;

import com.example.Model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderDetailResDTO {
    private String img;
    private String title;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
}
