package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <pre>
 * Class : JudgeDivision
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-06       홍길동           최초 생성
 * </pre>
 *
 * @author 홍길동(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_JUDGE_DIVSION")
@SequenceGenerator(
        name = "SEQ_JUDGE_DIVSION_ID_GENERATOR",
        sequenceName = "SEQ_JUDGE_DIVSION_ID",
        allocationSize = 1
)
public class JudgeDivision {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_JUDGE_DIVSION_ID_GENERATOR"
    )
    @Column(name = "JUDGE_DIVISION_ID")
    private int id;

    @Column(name = "JUDGE_DIVISION_DES")
    private String des;

}
