package com.steady.leisurethatapi.project.manage.dto;

import com.steady.leisurethatapi.database.entity.AccountInfo;
import com.steady.leisurethatapi.database.entity.Attachment;
import com.steady.leisurethatapi.database.entity.Bank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoResponseDTO {
    private int accountId;
    private String accountNumber;
    private String accountHolder;
    private Attachment accountAttachment;
    private Bank bank;

    public AccountInfoResponseDTO(AccountInfo accountInfo){
        this.accountId = accountInfo.getId();
        this.accountNumber = accountInfo.getAccountNumber();
        this.accountAttachment = accountInfo.getAttachment();
        this.bank = accountInfo.getBank();
    }
}
