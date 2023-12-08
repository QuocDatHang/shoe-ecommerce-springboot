package com.example.shoeEcommerceSpringboot.repository;

import com.example.shoeEcommerceSpringboot.Model.Filter;
import com.example.shoeEcommerceSpringboot.Model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product as p " +
            "WHERE ( p.title LIKE %:#{#filter.search}%) " +
            "AND (:#{#filter.companyName} = 'allCompany' OR p.company.nameCompany = :#{#filter.companyName}) " +
            "AND (:#{#filter.categoryName} = 'allCategory' OR p.category.nameCategory = :#{#filter.categoryName}) "+
            "AND (:#{#filter.colorName} = 'allColor' OR p.color.nameColor = :#{#filter.colorName}) " +
            "AND ((:#{#filter.minPrice} = 0 AND :#{#filter.maxPrice} = 0) " +
            "OR (:#{#filter.maxPrice} = :#{#filter.minPrice} AND :#{#filter.minPrice} > 0 AND p.newPrice >= :#{#filter.minPrice}) " +
            "OR (p.newPrice >= :#{#filter.minPrice} AND p.newPrice < :#{#filter.maxPrice}))")
    List<Product> filterProduct(@Param("filter") Filter filter);

}
