package com.steady.leisurethatapi.calculate.dto;

import java.util.List;

/**
 * <pre>
 * Class : MakerInfoDTO
 * Comment: 관리자 정산페이지 제작자 정보 DTO
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-08       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public class MakerInfoDTO {

    private String name;
    private String phone;
    private int businessId;
    private String bank;
    private String accountNumber;
    private int projectCount;
    private List<MakerProjectInfoDTO> makerProject;

    public MakerInfoDTO() {}

    public MakerInfoDTO(String name, String phone, int businessId, String bank, String accountNumber, int projectCount, List<MakerProjectInfoDTO> makerProject) {
        this.name = name;
        this.phone = phone;
        this.businessId = businessId;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.projectCount = projectCount;
        this.makerProject = makerProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    public List<MakerProjectInfoDTO> getMakerProject() {
        return makerProject;
    }

    public void setMakerProject(List<MakerProjectInfoDTO> makerProject) {
        this.makerProject = makerProject;
    }

    @Override
    public String toString() {
        return "MakerInfoDTO{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", businessId='" + businessId + '\'' +
                ", bank='" + bank + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", projectCount=" + projectCount +
                ", makerProject=" + makerProject +
                '}';
    }
}
