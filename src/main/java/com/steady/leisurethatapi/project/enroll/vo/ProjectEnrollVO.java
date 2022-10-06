package com.steady.leisurethatapi.project.enroll.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEnrollVO implements Serializable {
    private String name;
    private Date startDate;
    private Date endDate;
    private int targetAmount;
    private String url;
    private int categoryId;

    private MultipartFile[] businessFile;
    private String taxInvoiceEmail;

    private int bankId;
    private int accountNumber;
    private String accountHolder;
    private MultipartFile[] accountFile;

    private MultipartFile[] projectFile;

    private MultipartFile[] storyFile;
    private String storyContent;

    private String productName;
    private MultipartFile[] productFile;
    private String productDetail;

    private int rewardPrice;
    private int rewardMaxCount;
    private String rewardTitle;
    private String rewardContent;
    private java.util.Date rewardDate;
    private int rewardFee;
    private int rewardFeeFar;
}
