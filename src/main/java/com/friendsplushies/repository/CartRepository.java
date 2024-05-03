package com.friendsplushies.repository;

import com.friendsplushies.model.entity.Cart;
import com.friendsplushies.repository.custom.CartRepositoryCustom;
import com.friendsplushies.util.cruds.repository.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>, CartRepositoryCustom, IRepository<Cart> {

    void deleteAllByProductId(Long productId);

    void deleteAllByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM OrderCart oc WHERE oc.cartId in ?1")
    void deleteAllOrderCartByCartIds(List<Long> cartId);

    List<Cart> findAllByUserId(Long userId);
}
