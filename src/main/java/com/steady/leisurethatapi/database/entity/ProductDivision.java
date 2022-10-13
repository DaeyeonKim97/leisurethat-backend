package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_PRODUCT_DIVISION")
@SequenceGenerator(
        name = "SEQ_PRODUCT_DIVISION_ID_GENERATOR",
        sequenceName = "SEQ_PRODUCT_DIVISION_ID",
        allocationSize = 1
)
public class ProductDivision {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_PRODUCT_DIVISION_ID_GENERATOR"
    )
    @Column(name = "PRODUCT_DIVISION_ID")
    private int id;
    @Column(name = "PRODUCT_DIVISION_DETAIL")
    private String detail;
}
