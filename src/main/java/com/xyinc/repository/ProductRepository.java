package com.xyinc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyinc.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
