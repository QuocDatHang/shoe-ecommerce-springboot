package com.example.Model.dto;

import com.example.Model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderResDTO {
    private BigDecimal subtotal;
    private String shipping;
    private BigDecimal totalAmount;
    private String status;

    private String orderDate;
    private Customer customer;
    private List<OrderDetailResDTO> orderDetailResDTOList;
}
