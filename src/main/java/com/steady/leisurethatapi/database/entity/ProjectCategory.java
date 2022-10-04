package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_PROJECT_CATEGORY")
public class ProjectCategory {
    @Id
    @Column(name = "TBL_CATEGORY_ID")
    private int id;
    @Column(name = "PROJECT_CATEGORY_NAME")
    private String name;
}
