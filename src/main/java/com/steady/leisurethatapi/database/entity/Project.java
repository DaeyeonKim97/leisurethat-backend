package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_PROJECT")
@SequenceGenerator(
        name = "SEQ_PROJECT_ID_GENERATOR",
        sequenceName = "SEQ_PROJECT_ID",
        initialValue = 1,
        allocationSize = 1
)
public class Project implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_PROJECT_ID_GENERATOR"
    )
    @Column(name = "PROJECT_ID")
    private int id;
    @Column(name = "PROJECT_NAME")
    private String name;
    @Column(name = "PROJECT_START_DATE")
    private Date startDate;
    @Column(name = "PROJECT_END_DATE")
    private Date endDate;
    @Column(name = "PROJECT_TARGET_AMOUNT")
    private int targetAmount;
    @Column(name = "PROJECT_URL")
    private String projectURL;
    @ManyToOne
    @JoinColumn(name = "PROJECT_CATEGORY_ID")
    private ProjectCategory projectCategory;
    @Column(name = "PROJECT_VIEWS")
    private int views;
    @ManyToOne
    @JoinColumn(name = "PROJECT_STATUS_ID")
    private ProjectStatus status;
    @ManyToOne
    @JoinColumn(name = "BUSINESS_INFO_ID")
    private BusinessInfo businessInfo;
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_INFO_ID")
    private AccountInfo accountInfo;
    @OneToOne
    @JoinColumn(name = "ATC_ID")
    private Attachment attachment;
//    @Lob
    @Column(name = "PROJECT_REFUND_POLICY", columnDefinition = "LONG")
    private String refundPolicy;
    @Column(name = "PROJECT_INQUIRY_EMAIL")
    private String inquiryEmail;
    @Column(name = "PROJECT_INQUIRY_PHONE")
    private String inquiryPhone;
}
