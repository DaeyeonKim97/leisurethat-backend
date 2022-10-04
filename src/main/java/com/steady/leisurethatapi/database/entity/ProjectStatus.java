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
@Table(name = "TBL_PROJECT_STATUS")
public class ProjectStatus {
    @Id
    @Column(name = "PROJECT_STATUS_ID")
    private int id;
    @Column(name = "PROJECT_STATUS_DES")
    private String description;
}
