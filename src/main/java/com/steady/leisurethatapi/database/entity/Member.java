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
@Table(name = "TBL_MEMBER")
@SequenceGenerator(
        name = "SEQ_MEMBER_ID_GENERATOR",
        sequenceName = "SEQ_MEMBER_ID",
        allocationSize = 1
)
public class Member {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_MEMBER_ID_GENERATOR"
    )
    @Column(name = "MEMBER_ID")
    private int id;
    @Column(name = "MEMBER_USERNAME")
    private String username;
    @Column(name = "MEMBER_PASSWORD")
    private String password;
    @Column(name = "MEMBER_SEC_YN")
    private String secYn;
    @Column(name = "MEMBER_REG_DATE")
    private Date regDate;
    @Column(name = "MEMBER_EMAIL")
    private String email;
    @Column(name = "MEMBER_SEC_DATE")
    private Date secDate;
    @Column(name = "MEMBER_NAME")
    private String name;
    @Column(name = "MEMBER_CER_TOKEN")
    private String cerToken;
    @ManyToOne
    @JoinColumn(name = "SNS_ID")
    private SnsCategory snsCategory;
    @Column(name = "MEMBER_PHONE")
    private String phone;
    @OneToOne
    @JoinColumn(name = "MEMBER_PROFILE_ID", referencedColumnName = "ATC_ID")
    private Attachment profile;
    @Column(name = "MEMBER_ROLE")
    private String role;

}
