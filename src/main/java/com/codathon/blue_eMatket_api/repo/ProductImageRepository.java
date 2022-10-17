package com.codathon.blue_eMatket_api.repo;

import com.codathon.blue_eMatket_api.model.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImages, Integer> {

    @Query(value = "select * from product_images where product_code=?1 ",nativeQuery = true)
    List<ProductImages> findByProduct(String productCode);

}
