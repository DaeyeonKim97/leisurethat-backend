package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.OrderDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;

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
}
