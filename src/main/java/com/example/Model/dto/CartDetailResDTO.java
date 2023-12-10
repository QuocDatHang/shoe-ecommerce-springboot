package com.example.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailResDTO {
    private Long id;
    private Long productId;
    private String title;
    private String img;
    private String color;
    private BigDecimal amount;
    private Integer quantity;

}
