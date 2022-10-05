package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * <pre>
 * Class : Delivery
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
@Table(name = "TBL_DELIVERY")
@SequenceGenerator(
        name="SEQ_DELIVERY_ID_GENERATOR",
        sequenceName = "SEQ_DELIVERY_ID",
        allocationSize = 1
)
public class Delivery {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_DELIVERY_ID_GENERATOR"
    )
    @Column(name="DELIVERY_ID")
    private int deliveryId;

    @Column(name="DELIVERY_RECEIVER")
    private String deliveryReceiver;

    @Column(name="DELIVERY_BASIC_ADDRESS")
    private String deliveryBasicAddress;

    @Column(name="DELIVERY_DETAIL_ADDRESS")
    private String deliveryDetailAddress;

    @Column(name="CONTACT")
    private String contact;

    @Column(name="DELIVERY_BASIC_YN")
    private String deliveryBasicYn;

    @Column(name="POSTAL_CODE")
    private String postalCode;

    @Column(name="DELETE_YN")
    private String deleteYn;

    @Column(name="DELIVERY_REG_DATE")
    private Date deliveryRegDate;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

}
