package com.flourish.store.repository;

import com.flourish.store.domain.OrderItem;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data  repository for the OrderItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    String query = "FROM order_item i LEFT JOIN product_order po on i.order_id=po.id " +
        "LEFT JOIN customer c on c.id=po.customer_id " +
        "LEFT JOIN jhi_user u on u.id=c.user_id WHERE u.login=?1";

    @Query(value = "select i.* " + query,
        countQuery = "SELECT count(i.*) " + query,
        nativeQuery=true)
    Page<OrderItem> findAllByOrderCustomerUserLogin(String login, Pageable pageable);

}
