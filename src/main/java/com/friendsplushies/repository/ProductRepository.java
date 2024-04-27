package com.friendsplushies.repository;

import com.friendsplushies.model.entity.Product;
import com.friendsplushies.repository.custom.ProductRepositoryCustom;
import com.friendsplushies.util.cruds.repository.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, IRepository<Product>, ProductRepositoryCustom {
}
