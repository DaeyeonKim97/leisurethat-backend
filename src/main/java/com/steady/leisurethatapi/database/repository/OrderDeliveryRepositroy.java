package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.calculate.dto.DeliveryStatusCount;
import com.steady.leisurethatapi.database.entity.OrderDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.*;
import java.util.List;

/**
 * <pre>
 * Class : OrderDeliveryRepositroy
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-05       홍길동           최초 생성
 * </pre>
 *
 * @author 홍길동(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public interface OrderDeliveryRepositroy extends JpaRepository<OrderDelivery, Integer> {

    public OrderDelivery findByOrderId(int id);

    @Query(value="select \n" +
            "new com.steady.leisurethatapi.calculate.dto.DeliveryStatusCount(od.deliveryStatus, count(od.deliveryStatus))\n" +
            "from OrderDelivery od \n " +
            "where od.order.project.id = :id \n " +
            "group by od.deliveryStatus")
    List<DeliveryStatusCount> findByProjectId(int id);
}
