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
@Table(name = "TBL_ORDER")
@SequenceGenerator(
        name = "SEQ_ORDER_ID_GENERATOR",
        sequenceName = "SEQ_ORDER_ID",
        allocationSize = 1
)
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ORDER_ID_GENERATOR"
    )
    @Column(name = "ORDER_ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @Column(name = "ORDER_DATE")
    private Date orderDate;
    @Column(name = "ORDER_STATUS")
    private String status;
    @ManyToOne
    @JoinColumn(name = "REWARD_ID")
    private Reward reward;
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;
}
