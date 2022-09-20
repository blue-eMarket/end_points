package com.codathon.blue_eMatket_api.repo;

import com.codathon.blue_eMatket_api.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
