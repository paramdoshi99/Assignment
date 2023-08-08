package com.api.repository;

import com.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.entity.CatalogItem;

import java.util.List;

@Repository
public interface CatalogItemRepository extends JpaRepository<CatalogItem, Long> {
    List<CatalogItem> findById(String Id);

    @Query("SELECT c.name FROM CatalogItem c WHERE c.Id = :Id")
    List<String> findNameById(@Param("Id") String Id);
}

