package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_ORDER_DETAIL")
@SequenceGenerator(
        name = "SEQ_ORDER_DETAIL_ID_GENERATOR",
        sequenceName = "SEQ_ORDER_DETAIL_ID",
        allocationSize = 1
)
public class OrderDetail {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ORDER_DETAIL_ID_GENERATOR"
    )
    @Column(name="ORDER_DETAIL_ID")
    private int id;

    @Column(name="REWARD_AMOUNT")
    private int rewardAmount;

    @ManyToOne
    @JoinColumn(name="REWARD_ID")
    private Reward reward;

    @OneToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @Column(name="ORDER_DETAIL_STATUS")
    private String status;

}
