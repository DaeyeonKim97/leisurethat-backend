package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_BUSINESS_INFO")
@SequenceGenerator(
        name = "SEQ_BUSINESS_INFO_ID_GENERATOR",
        sequenceName = "SEQ_BUSINESS_INFO_ID",
        allocationSize = 1
)
public class BusinessInfo {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_BUSINESS_INFO_ID_GENERATOR"
    )
    @Column(name = "BUSINESS_INFO_ID")
    private int id;
    @OneToOne
    @JoinColumn(name = "ATC_ID")
    private Attachment attachment;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @Column(name = "TAX_INVOICE_EMAIL")
    private String taxInvoiceEmail;
}
