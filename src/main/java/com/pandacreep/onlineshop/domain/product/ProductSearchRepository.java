package com.pandacreep.onlineshop.domain.product;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSearchRepository extends PagingAndSortingRepository<Product, Integer>,
        JpaSpecificationExecutor<Product> {}
