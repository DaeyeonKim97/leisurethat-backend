package com.steady.leisurethatapi.project.manage.dto;

import com.steady.leisurethatapi.database.entity.Product;
import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.entity.Reward;
import com.steady.leisurethatapi.database.entity.Story;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetailResponseDTO {
    private AccountInfoResponseDTO accountInfo;
    private BusinessInfoResponseDTO businessInfo;
    private MemberResponseDTO member;
    private ProjectResponseDTO project;
    private List<ProductResponseDTO> productList;
    private List<RewardResponseDTO> rewardList;
    private List<StoryResponseDTO> storyList;
}
