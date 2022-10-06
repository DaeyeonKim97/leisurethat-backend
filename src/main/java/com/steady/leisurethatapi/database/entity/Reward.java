package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_REWARD")
@SequenceGenerator(
        name = "SEQ_REWARD_ID_GENERATOR",
        sequenceName = "SEQ_REWARD_ID",
        allocationSize = 1
)
public class Reward {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_REWARD_ID_GENERATOR"
    )
    @Column(name = "REWARD_ID")
    private int id;
    @Column(name = "REWARD_PRICE")
    private int price;
    @Column(name = "REWARD_TITLE")
    private String title;
    @Column(name = "REWARD_SERVE_COUNT")
    private int serveCount;
    @Column(name = "REWARD_MAX_COUNT")
    private int maxCount;
    @Column(name = "REWARD_DATE")
    private Date rewardDate;
    @Column(name = "REWARD_FEE")
    private int rewardFee;
    @Column(name = "REWARD_FEE_FAR")
    private int rewardFeeFar;
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;
}
