package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

/**
 * <pre>
 * Class : Reject
 * Comment: Reject Entity
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-07       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_REJECT")
@SequenceGenerator(
        name = "SEQ_REJECT_ID_GENERATOR",
        sequenceName = "SEQ_REJECT_ID",
        allocationSize = 1
)
public class Reject {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "SEQ_REJECT_ID_GENERATOR"
    )
    @Column(name = "REJECT_ID")
    private int id;

    @Column(name = "REJECT_TITLE")
    private String title;

    @Column(name = "REJECT_CONTENT")
    private String content;

    @Column(name = "REJECT_DATE")
    private Date date;

    @OneToOne
    @JoinColumn(name = "JUDGE_ID")
    private Judge judge;
}
