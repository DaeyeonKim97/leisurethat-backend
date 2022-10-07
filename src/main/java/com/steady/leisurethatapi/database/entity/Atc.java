package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

/**
 * <pre>
 * Class : Atc
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
@Table(name = "TBL_ATC")
@SequenceGenerator(
        name = "SEQ_ATC_ID_GENERATOR",
        sequenceName = "SEQ_ATC_ID",
        allocationSize = 1
)
public class Atc {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ATC_ID_GENERATOR"
    )
    @Column(name = "ATC_ID")
    private int id;

    @Column(name = "ATC_ORIGINAL_NAME")
    private String originalName;

    @Column(name = "ATC_MODIFY_NAME")
    private String modifyName;

    @Column(name = "DOWNLOAD_ADDRESS")
    private String downloadAddress;

    @Column(name = "UPLOAD_DATE")
    private Date uploadDate;

//    @Column(name = "ATC_DEVISION")
//    private String devision;
}
