package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

/**
 * <pre>
 * Class : Judge
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-06       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_JUDGE")
@SequenceGenerator(
        name = "SEQ_JUDGE_ID_GENERATOR",
        sequenceName = "SEQ_JUDGE_ID",
        allocationSize = 1
)
public class Judge {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_JUDGE_ID_GENERATOR"
    )
    @Column(name = "JUDGE_ID")
    private int id;

    @Column(name = "JUDGE_TITLE")
    private String title;

    @Column(name = "JUDGE_CONTENT")
    private String content;

    @Column(name = "JUDGE_REG_DATE")
    private Date regDate;

    @ManyToOne
    @JoinColumn(name = "JUDGE_DIVISION_ID")
    private JudgeDivision judgeDivision;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @Column(name = "JUDGE_PRC_DATE")
    private Date prcDate;

    @OneToOne
    @JoinColumn(name = "ATC_ID")
    private Atc atc;

//    @Column(name = "JUDGE_DIVISION_DES")
//    private String divisionDes;
}
