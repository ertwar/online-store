package com.flourish.store.repository;

import com.flourish.store.domain.ProductOrder;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

/**
 * Spring Data  repository for the ProductOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

    @Query(value = "select po.* from product_order po left join customer c on c.id=po.customer_id " +
        "left join jhi_user u on u.id=c.user_id " +
        "where u.login=?1",
        countQuery = "SELECT count(*) FROM product_order po " +
            "left join customer c on c.id=po.customer_id " +
            "left join jhi_user u on u.id=c.user_id where u.login=?1",
        nativeQuery=true)
    Page<ProductOrder> findAllByCustomerUserLogin(String login, Pageable pageable);

    String query = "from product_order po left join customer c on c.id=po.customer_id " +
        "left join jhi_user u on u.id=c.user_id where po.id=?1 and u.login=?2";

    @Query(value = "select po.* " + query,
            countQuery = "select count(*) " + query,
            nativeQuery=true)
    Optional<ProductOrder> findOneByIdAndCustomerUserLogin(Long id, String login);
}
