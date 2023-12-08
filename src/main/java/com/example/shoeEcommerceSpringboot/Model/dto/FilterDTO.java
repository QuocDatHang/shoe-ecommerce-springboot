package com.example.shoeEcommerceSpringboot.Model.dto;

import com.example.shoeEcommerceSpringboot.Model.Filter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterDTO {
    private String search;
    private String companyName;
    private String categoryName;
    private String colorName;
    private Long minPrice;
    private Long maxPrice;

    public Filter toFilter(FilterDTO filterDTO) {
        return new Filter().setSearch(filterDTO.getSearch())
                .setCompanyName(filterDTO.companyName)
                .setCategoryName(filterDTO.categoryName)
                .setColorName(filterDTO.colorName)
                .setMinPrice(BigDecimal.valueOf(filterDTO.minPrice))
                .setMaxPrice(new BigDecimal(filterDTO.maxPrice))
                ;
    }
}
