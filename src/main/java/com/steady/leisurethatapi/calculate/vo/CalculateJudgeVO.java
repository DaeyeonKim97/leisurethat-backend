package com.steady.leisurethatapi.calculate.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * Class : CalculateJudgeVO
 * Comment: 정산 심사 폼
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
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateJudgeVO implements Serializable {

    private int projectId;
    private int calculateId;
    private String calculateRound;
    private String calculateJudgeStatus;
    private String calculateRejectTitle;
    private String calculateRejectContent;

}
