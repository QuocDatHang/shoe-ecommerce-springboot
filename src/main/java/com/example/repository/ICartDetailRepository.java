package com.example.repository;

import com.example.Model.Product;
import com.example.Model.dto.CartDetailResDTO;
import com.example.Model.Cart;
import com.example.Model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {
    Optional<CartDetail> findByCartAndProduct(Cart cart, Product product);

    @Query("SELECT SUM(cd.amount * cd.quantity) FROM CartDetail AS cd WHERE cd.cart = :cart ")
    BigDecimal getTotalAmount(Cart cart);

    @Query("SELECT NEW com.example.Model.dto.CartDetailResDTO(" +
                "cd.id, " +
                "cd.product.id, " +
                "cd.product.title, " +
                "cd.product.img, " +
                "cd.product.color.nameColor, " +
                "cd.amount, " +
                "cd.quantity" +
            ") FROM CartDetail AS cd " +
            "WHERE cd.cart = :cart")
    List<CartDetailResDTO> getAllCartDetailResDTOByCart(@Param("cart") Cart cart);

    List<CartDetail> findAllByCart(Cart cart);
}

