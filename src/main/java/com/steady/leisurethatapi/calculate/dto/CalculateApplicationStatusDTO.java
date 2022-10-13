package com.steady.leisurethatapi.calculate.dto;

import java.util.Date;

/**
 * <pre>
 * Class : CalculateApplicationStatusDTO
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-06       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 * */
public class CalculateApplicationStatusDTO {

    private int calculateId;
    private String calculateRound;
    private String status;
    private long amount;
    private Date regDate;
    private Date giveDate;

    public CalculateApplicationStatusDTO() {}

    public CalculateApplicationStatusDTO(int calculateId, String calculateRound, String status, long amount, Date regDate, Date giveDate) {
        this.calculateId = calculateId;
        this.calculateRound = calculateRound;
        this.status = status;
        this.amount = amount;
        this.regDate = regDate;
        this.giveDate = giveDate;
    }

    public int getCalculateId() {
        return calculateId;
    }

    public void setCalculateId(int calculateId) {
        this.calculateId = calculateId;
    }

    public String getCalculateRound() {
        return calculateRound;
    }

    public void setCalculateRound(String calculateRound) {
        this.calculateRound = calculateRound;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getGiveDate() {
        return giveDate;
    }

    public void setGiveDate(Date giveDate) {
        this.giveDate = giveDate;
    }

    @Override
    public String toString() {
        return "CalculateApplicationStatusDTO{" +
                "calculateId=" + calculateId +
                ", calculateRound='" + calculateRound + '\'' +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", regDate=" + regDate +
                ", giveDate=" + giveDate +
                '}';
    }
}
