package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_SNS_CATEGORY")
@SequenceGenerator(
        name = "SEQ_SNS_CATEGORY_GENERATOR",
        sequenceName = "SEQ_SNS_CATEGORY",
        allocationSize = 1
)
public class SnsCategory {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_SNS_CATEGORY_GENERATOR"
    )
    @Column(name = "SNS_CATEGORY_ID")
    private int id;
    @Column(name = "SNS_CATEGORY_NAME")
    private String name;
}
