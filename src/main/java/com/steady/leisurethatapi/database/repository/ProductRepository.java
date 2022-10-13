package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
