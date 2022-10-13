package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_PAYMENT")
@SequenceGenerator(
        name = "SEQ_PAYMENT_ID_GENERATOR",
        sequenceName = "SEQ_PAYMENT_ID",
        allocationSize = 1
)
public class Payment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_PAYMENT_ID_GENERATOR"
    )
    @Column(name = "PAYMENT_ID")
    private int id;

    @Column(name = "PAYMENT_DIVISION")
    private String division;

    @Column(name = "PAYMENT_PRICE")
    private Integer price;

    @Column(name = "PAYMENT_COUNT")
    private Integer conunt;

    @Column(name = "PAYMENT_STATUS")
    private String paymentState;

    @Column(name = "PAYMENT_RESERVE_DATE")
    private Date paymentReserveDate;

    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    @OneToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(name = "PAYMENT_MODIFY_DATE")
    private Date modifyDate;

    @Column(name = "BILLING_KEY")
    private String billingKey;

    @Column(name = "CARD_PAYMENT_TOKEN")
    private String paymentToken;

    @Column(name = "REFUND_REASON")
    private String refundReason;

    @Column(name = "REFUND_REQUEST_DATE")
    private Date refundRequestDate;

    @Column(name = "REFUND_DATE")
    private Date refundDate;

}
