package com.friendsplushies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.friendsplushies.model.entity.Cart;
import com.friendsplushies.repository.custom.CartRepositoryCustom;

import com.friendsplushies.util.cruds.repository.IRepository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>, IRepository<Cart>, CartRepositoryCustom {
}

