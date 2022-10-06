package com.steady.leisurethatapi.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Date;

/**
 * <pre>
 * Class : Calculate
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
@Table(name = "TBL_CAL")
@SequenceGenerator(
        name = "SEQ_CAL_ID_GENERATOR",
        sequenceName = "SEQ_CAL_ID",
        allocationSize = 1
)
public class Calculate {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_CAL_ID_GENERATOR"
    )
    @Column(name = "CAL_ID")
    private int id;

    @Column(name = "CAL_DIVISION")
    private String division;

    @Column(name = "CAL_TOTAL")
    private long total;

    @Column(name = "CAL_PRE_AMOUNT")
    private long preAmount;

    @Column(name = "CAL_BALANCE")
    private Integer balance;

    @Column(name = "CAL_POST_AMOUNT")
    private Integer postAmount;

    @Column(name = "CAL_GIVE_DATE")
    private Date giveDate;

    @OneToOne
    @JoinColumn(name = "JUDGE_ID")
    private Judge judge;
}
