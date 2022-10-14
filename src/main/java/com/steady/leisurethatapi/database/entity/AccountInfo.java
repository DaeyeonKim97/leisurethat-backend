package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_ACCOUNT_INFO")
@SequenceGenerator(
        name = "SEQ_BUSINESS_INFO_ID_GENERATOR",
        sequenceName = "SEQ_BUSINESS_INFO_ID",
        allocationSize = 1
)
public class AccountInfo {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_BUSINESS_INFO_ID_GENERATOR"
    )
    @Column(name = "ACCOUNT_INFO_ID")
    private int id;
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @Column(name = "ACCOUNT_HOLDER")
    private String accountHolder;
    @OneToOne
    @JoinColumn(name = "ATC_ID")
    private Attachment attachment;
    @ManyToOne
    @JoinColumn(name = "BUSINESS_INFO_ID")
    private BusinessInfo businessInfo;
    @ManyToOne
    @JoinColumn(name = "BANK_ID")
    private Bank bank;
}
