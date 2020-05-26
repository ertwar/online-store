package com.flourish.store.repository;

import com.flourish.store.domain.Shipment;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

/**
 * Spring Data  repository for the Shipment entity.
 */


@SuppressWarnings("unused")
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    String query = "FROM shipment s LEFT JOIN invoice i on i.id=s.invoice_id " +
        "LEFT JOIN product_order po on i.order_id=po.id " +
        "LEFT JOIN customer c on c.id=po.customer_id " +
        "LEFT JOIN jhi_user u on u.id=c.user_id WHERE u.login=?1";

    @Query(value = "select s.* " + query,
        countQuery = "SELECT count(s.*) " + query,
        nativeQuery=true)
    Page<Shipment> findAllByInvoiceOrderCustomerUserLogin(String login, Pageable pageable);

    //String findOneQuery = query.toString().replaceAll("?1","?2").concat(" AND s.id=?1");

    //@Query(value = "select po.* " + findOneQuery,
    //    countQuery = "select count(*) " + query.replace("?1","?2") + " AND s.id=?1"),
    //    nativeQuery=true)
    Optional<Shipment> findOneByIdAndInvoiceOrderCustomerUserLogin(Long id, String login);
}
