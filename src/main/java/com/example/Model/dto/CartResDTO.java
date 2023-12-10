package com.example.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResDTO {
    private BigDecimal totalAmount;
    private List<CartDetailResDTO> cartDetailResDTOList;
}
