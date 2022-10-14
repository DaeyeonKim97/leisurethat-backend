package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_PRODUCT_NOTICE")
@SequenceGenerator(
        name = "SEQ_PRODUCT_NOTICE_ID_GENERATOR",
        sequenceName = "SEQ_PRODUCT_NOTICE_ID",
        allocationSize = 1
)
public class ProductNotice {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_PRODUCT_NOTICE_ID_GENERATOR"
    )
    @Column(name = "PRODUCT_NOTICE_ID")
    private int id;
    @Column(name = "PRODUCT_NOTICE_CONTENT")
    private String content;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_DIVISION_ID")
    private ProductDivision division;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
