package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TBL_ORDER_DELIVERY")
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
    @Column(name = "ORDER_DELIVERY_ID")
    private int deliveryId;

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @Column(name = "WAYBILL_ID")
    private Long waybillId;

    @Column(name = "DELIVERY_STATUS")
    private String deliveryState;

    @Column(name = "ORDER_DELIVERY_DATE")
    private Date deliveryRegistDate;

    @OneToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(name = "COURIER_ID")
    private Integer courier;

    @Column(name = "DELIVERY_DATE")
    private Date deliveryDate;

}
