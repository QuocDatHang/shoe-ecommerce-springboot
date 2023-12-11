package com.example.Model.dto;

import com.example.Model.Filter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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
    private Integer page;
    private Integer size;
    private String sortField;
    private String direction;

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
