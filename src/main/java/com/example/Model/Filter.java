package com.example.Model;

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
public class Filter {
    private String search;
    private String companyName;
    private String categoryName;
    private String colorName;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

}
