package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Order;
import com.steady.leisurethatapi.database.entity.OrderDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Integer> {

    @Query(value = "SELECT \n" +
            "*"+
            "FROM TBL_ORDER_DELIVERY D \n" +
            "LEFT JOIN TBL_ORDER O ON (D.ORDER_ID = o.order_id) \n" +
            "LEFT JOIN TBL_MEMBER M ON(O.MEMBER_ID = M.MEMBER_ID)\n" +
            "LEFT JOIN TBL_DELIVERY V ON (D.DELIVERY_ID = V.DELIVERY_ID)" +
            "WHERE D.ORDER_ID = :id", nativeQuery = true)
    public OrderDelivery findByOrderId(@Param("id") int id);

    public OrderDelivery findByOrder(Order order);
}
