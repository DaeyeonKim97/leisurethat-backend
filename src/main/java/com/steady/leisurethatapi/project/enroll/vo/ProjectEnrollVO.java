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
    private String startDate;
    private String endDate;
    private int targetAmount;
    private String url;
    private int categoryId;

    private MultipartFile[] businessFile;
    private String taxInvoiceEmail;

    private int bankId;
    private String  accountNumber;
    private String accountHolder;
    private MultipartFile accountFile;

    private MultipartFile projectFile;
    private String refundPolicy;
    private String inquiryEmail;
    private String inquiryPhone;

    private MultipartFile storyFile;
    private String storyTitle;
    private String storyContent;

    private String productName;
    private MultipartFile productFile;
    private String productDetail;

    private int rewardPrice;
    private int rewardMaxCount;
    private int rewardServeCount;
    private String rewardTitle;
    private String rewardContent;
    private String rewardDate;
    private int rewardFee;
    private int rewardFeeFar;
}
