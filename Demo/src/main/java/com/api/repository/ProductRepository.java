package com.api.repository;

import com.api.entity.Customer;
import com.api.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findByProductId(String productId);

    @Query("SELECT c.productId FROM Product c WHERE c.productId = :productId")
    List<String> findProductNameByProductId(@Param("productId") String productId);

//    @Query("UPDATE Product c SET c.emailId = ?1 WHERE c.customerId = ?2")
//    @Modifying
//    @Transactional
//    Integer updateCustomerEmailId(String updateCustomerByEmailId, Integer customerId);
}
