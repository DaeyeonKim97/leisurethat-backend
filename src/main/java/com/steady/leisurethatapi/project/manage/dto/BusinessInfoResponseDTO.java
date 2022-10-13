package com.steady.leisurethatapi.project.manage.dto;

import com.steady.leisurethatapi.database.entity.Attachment;
import com.steady.leisurethatapi.database.entity.BusinessInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessInfoResponseDTO {
    private int businessInfoId;
    private String taxInvoiceMail;
    private Attachment businessInfoAttachment;

    public BusinessInfoResponseDTO(BusinessInfo businessInfo){
        this.businessInfoId = businessInfo.getId();
        this.taxInvoiceMail = businessInfo.getTaxInvoiceEmail();
        this.businessInfoAttachment = businessInfo.getAttachment();
    }
}
