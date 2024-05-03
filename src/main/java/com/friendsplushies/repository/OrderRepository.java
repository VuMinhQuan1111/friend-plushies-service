package com.friendsplushies.repository;

import com.friendsplushies.model.entity.Order;
import com.friendsplushies.model.request.SearchRequest;
import com.friendsplushies.repository.custom.OrderRepositoryCustom;
import com.friendsplushies.util.cruds.repository.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, IRepository<Order> , OrderRepositoryCustom {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO order_cart (order_id, cart_id) VALUES (?1, ?2)", nativeQuery = true)
    void assignOrder(Long orderId, Long cartId);

    @Query(value = "SELECT DISTINCT o FROM Order o inner join o.orderCarts oc inner join oc.cart c WHERE c.userId = ?1")
    List<Order> searchByUserId(Long userId);
    @Query(value = "SELECT DISTINCT count(o) FROM Order o inner join o.orderCarts oc inner join oc.cart c WHERE c.userId = ?1")
    Long countByUserId(Long userId);
}
