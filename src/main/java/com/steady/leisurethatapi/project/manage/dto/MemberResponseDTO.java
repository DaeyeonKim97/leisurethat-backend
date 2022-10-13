package com.steady.leisurethatapi.project.manage.dto;

import com.steady.leisurethatapi.database.entity.Attachment;
import com.steady.leisurethatapi.database.entity.Member;
import com.steady.leisurethatapi.database.entity.SnsCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDTO {
    private int memberId;
    private String username;
    private Date memberRegDate;
    private String memberEmail;
    private String memberName;
    private SnsCategory snsCategory;
    private String memberPhone;
    private Attachment memberAttachment;
    private String memberRole;

    public MemberResponseDTO(Member member){
        this.memberId = member.getId();
        this.username = member.getUsername();
        this.memberRegDate = member.getRegDate();
        this.memberEmail = member.getEmail();
        this.memberName = member.getName();
        this.snsCategory = member.getSnsCategory();
        this.memberPhone = member.getPhone();
        this.memberAttachment = member.getProfile();
        this.memberRole = member.getRole();
    }
}
