package com.steady.leisurethatapi.calculate.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * <pre>
 * Class : CalculatePreApplicationVO
 * Comment: 1차 정산 신청 폼
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-09       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 * */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateApplicationVO implements Serializable {

    private int projectId;
    private String calculateStatus;
    private String calculateDivision;

    private Integer totalCalAmount;
    private Integer preCalAmount;
    private Integer balance;
    private Integer postCalAmount;
    private MultipartFile preCalApplicationFile;

    private String title;
    private String content;

}
