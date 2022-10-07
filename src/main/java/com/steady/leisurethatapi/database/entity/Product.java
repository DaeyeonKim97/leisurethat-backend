package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TBL_PRODUCT")
@SequenceGenerator(
        name = "SEQ_PRODUCT_ID_GENERATOR",
        sequenceName = "SEQ_PRODUCT_ID",
        allocationSize = 1
)
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_PRODUCT_ID_GENERATOR"
    )
    @Column(name = "PRODUCT_ID")
    private int id;
    @Column(name = "PRODUCT_NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;
    @OneToOne
    @JoinColumn(name = "ATC_ID")
    private Attachment attachment;
    @Column(name = "PRODUCT_DETAIL")
    private String detail;
}
