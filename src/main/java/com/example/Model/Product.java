package com.example.Model;

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
@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String img;
    @JoinColumn(name = "company_id")
    @ManyToOne()
    private Company company;
    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;
    @JoinColumn(name = "color_id")
    @ManyToOne
    private Color color;
    private Integer reviews;
    private Integer star;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal prevPrice;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal newPrice;

}
