package com.sai.products.repository;

import com.sai.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findAllByIdInOrderById(List<Integer> productIdList);
}
