package com.example.redstore.repository;

import com.example.redstore.domain.CartItem;
import com.example.redstore.service.dto.CartDto;
import com.example.redstore.service.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {
    List<CartItem> findByCartId(Long cartId);


    @Query(value = "SELECT ci.* FROM railway.cart_item ci " +
            " join railway.product p on ci.product_id = p.id " +
            " where p.user_id = :productUserId", nativeQuery = true)
    List<CartItem> findCartItemByProductUserId(Long productUserId);


    @Query(value = "Select ci.* from railway.cart_item ci where product_id = :product and cart_id = :cart", nativeQuery = true)
    Optional<CartItem> findByProductAndCart(Long product, Long cart);
}