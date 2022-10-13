package com.steady.leisurethatapi.database.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_DELIVERY")
@SequenceGenerator(
        name = "SEQ_DELIVERY_ID_GENERATOR",
        sequenceName = "SEQ_DELIVERY_ID",
        allocationSize = 1
)
public class Delivery {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_DELIVERY_ID_GENERATOR"
    )
    @Column(name = "DELIVERY_ID")
    private int id;

    @Column(name="DELIVERY_RECEIVER")
    private String receiver;

    @Column(name = "DELIVERY_BASIC_ADDRESS")
    private String basicAddress;

    @Column(name = "DELIVERY_DETAIL_ADDRESS")
    private String detailAddress;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "DELIVERY_BASIC_YN")
    private String deliveryBasic;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "DELETE_YN")
    private String deleteYn;

    @Column(name = "DELIVERY_REG_DATE")
    private Date registDate;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
