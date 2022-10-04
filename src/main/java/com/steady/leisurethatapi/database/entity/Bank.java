package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_BANK")
public class Bank {
    @Id
    @Column(name="BANK_ID")
    private int id;
    @Column(name = "BANK_NAME")
    private String name;
}
