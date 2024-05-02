package com.friendsplushies.repository;

import com.friendsplushies.model.entity.Cart;
import com.friendsplushies.repository.custom.CartRepositoryCustom;
import com.friendsplushies.util.cruds.repository.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>, CartRepositoryCustom, IRepository<Cart> {

    void deleteAllByProductId(Long productId);
}
