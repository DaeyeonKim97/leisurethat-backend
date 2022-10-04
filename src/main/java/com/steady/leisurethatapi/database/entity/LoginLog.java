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
@Table(name = "TBL_LOGIN_LOG")
@SequenceGenerator(
        name = "SEQ_LOGIN_LOG_ID_GENERATOR",
        sequenceName = "SEQ_LOGIN_LOG_ID",
        allocationSize = 1
)
public class LoginLog {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_LOGIN_LOG_ID_GENERATOR"
    )
    @Column(name = "LOGIN_LOG_ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member memberId;
    @Column(name = "LOGIN_DATE")
    private Date loginDate;
}
