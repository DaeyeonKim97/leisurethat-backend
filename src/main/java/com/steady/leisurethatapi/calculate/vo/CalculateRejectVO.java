package com.steady.leisurethatapi.calculate.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * Class : CalculateRejectVO
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-10       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateRejectVO implements Serializable  { //직렬화

    private int judgeId;
    private int rejectId;
    private String rejectContent;

}
