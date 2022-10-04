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
@Table(name = "TBL_ATC")
@SequenceGenerator(
        name = "SEQ_ATC_ID_GENERATOR",
        sequenceName = "SEQ_ATC_ID",
        allocationSize = 1
)
public class Attachment {
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
//    @Column(name = "ATC_DIVISION")
//    private String division;
//    @ManyToOne
//    @JoinColumn(name = "PROJECT_ID")
//    private Project project;
//    @ManyToOne
//    @JoinColumn(name = "STORY_ID")
//    private Story story;
//    @ManyToOne
//    @JoinColumn(name = "PRODUCT_ID")
//    private Product product;
//    @ManyToOne
//    @JoinColumn(name = "JUDGE_REQ_ID", referencedColumnName = "JUDGE_ID")
//    private Judge judge;
//    @ManyToOne
//    @JoinColumn(name = "ACCOUNT_ID")
//    private Account account;
}
