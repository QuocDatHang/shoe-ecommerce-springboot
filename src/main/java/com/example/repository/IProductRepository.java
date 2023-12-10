package com.example.repository;

import com.example.Model.Filter;
import com.example.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product as p " +
            "WHERE ( p.title LIKE %:#{#filter.search}%) " +
            "AND (:#{#filter.companyName} = 'allCompany' OR p.company.nameCompany = :#{#filter.companyName}) " +
            "AND (:#{#filter.categoryName} = 'allCategory' OR p.category.nameCategory = :#{#filter.categoryName}) "+
            "AND (:#{#filter.colorName} = 'allColor' OR p.color.nameColor = :#{#filter.colorName}) " +
            "AND ((:#{#filter.minPrice} = 0 AND :#{#filter.maxPrice} = 0) " +
            "OR (:#{#filter.maxPrice} = :#{#filter.minPrice} AND :#{#filter.minPrice} > 0 AND p.newPrice >= :#{#filter.minPrice}) " +
            "OR (p.newPrice >= :#{#filter.minPrice} AND p.newPrice < :#{#filter.maxPrice}))")
    Page<Product> filterProduct(@Param("filter") Filter filter, Pageable pageable);

}
