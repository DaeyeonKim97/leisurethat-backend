package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

/**
 * <pre>
 * Class : OrderDelivery
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_ORDER_DELIVERY")
@SequenceGenerator(
        name = "SEQ_ORDER_DELIVERY_ID_GENERATOR",
        sequenceName = "SEQ_ORDER_DELIVERY_ID",
        allocationSize = 1
)
public class OrderDelivery {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ORDER_DELIVERY_ID_GENERATOR"
    )
    @Column(name="ORDER_DELIVERY_ID")
    private int orderDeliveryId;

    @Column(name="ORDER_DELIVERY_DATE")
    private Date orderDeliveryDate;

    @Column(name="DELIVERY_DATE")
    private Date deliveryDate;

    @ManyToOne
    @JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;

    @Column(name="DELIVERY_STATUS")
    private String delivertStatus;

    @Column(name="COURIER_ID")
    private int courierId;

    @Column(name="WAYBILL_ID")
    private long waybillId;

    @OneToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

}
