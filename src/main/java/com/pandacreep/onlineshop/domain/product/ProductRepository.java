package com.pandacreep.onlineshop.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>,
        JpaRepository<Product, Integer> {
    Page<Product> findAllByCategoryId(int categoryId, Pageable pageable);
}
