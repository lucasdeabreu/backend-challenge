package com.invillia.acme.repositories;

import com.invillia.acme.entities.Order;
import com.invillia.acme.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o " +
            "where " +
            "(:address is null or lower(o.address) like concat(lower(:address), '%')) " +
            "and (:confirmationDate is null or o.confirmationDate = :confirmationDate )" +
            "and (:status is null or o.status = :status)")
    List<Order> findByParameters(@Param("address") String address,
                                 @Param("status") OrderStatus status,
                                 @Param("confirmationDate") LocalDateTime confirmationDate);

}
