package com.steady.leisurethatapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_STORY")
@SequenceGenerator(
        name = "SEQ_STORY_ID_GENERATOR",
        sequenceName = "SEQ_STORY_ID",
        allocationSize = 1
)
public class Story {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_STORY_ID_GENERATOR"
    )
    @Column(name = "STORY_ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;
    @Column(name = "STORY_TITLE")
    private String title;
    @Column(name = "STORY_CONTENT")
    private String content;
    @Column(name = "STORY_VIEWS")
    private int views;
    @Column(name = "STORY_DATE")
    private Date date;
    @Column(name = "STORY_YN")
    private String yN;
    @OneToOne
    @JoinColumn(name = "ATC_ID")
    private Attachment attachment;
}
