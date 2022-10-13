package com.steady.leisurethatapi.calculate.dto;

/**
 * <pre>
 * Class : CalculateAmountDTO
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-06       홍길동           최초 생성
 * </pre>
 *
 * @author 홍길동(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public class CalculateAmountResultDTO {

    private Long total;
    private Long actualAmount;


    public CalculateAmountResultDTO() {}

    public CalculateAmountResultDTO(Long total, Long actualAmount) {
        this.total = total;
        this.actualAmount = actualAmount;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Long actualAmount) {
        this.actualAmount = actualAmount;
    }

    @Override
    public String toString() {
        return "CalculateAmountDTO{" +
                "total=" + total +
                ", actualAmount=" + actualAmount +
                '}';
    }
}
