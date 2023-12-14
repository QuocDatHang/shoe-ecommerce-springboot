package com.example.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String img;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    private Integer reviews;
    private Integer star;

    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal prevPrice;

    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal newPrice;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductImage> images;
}
