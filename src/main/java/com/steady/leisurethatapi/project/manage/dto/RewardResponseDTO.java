package com.steady.leisurethatapi.project.manage.dto;

import com.steady.leisurethatapi.database.entity.Reward;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardResponseDTO {
    private int rewardId;
    private String rewardTitle;
    private int rewardServeCount;
    private int rewardMaxCount;
    private Date rewardDate;
    private int rewardFee;
    private int rewardFeeFar;
    private String rewardContent;
    private int rewardPrice;
    public RewardResponseDTO(Reward reward){
        this.rewardId = reward.getId();
        this.rewardTitle = reward.getTitle();
        this.rewardServeCount = reward.getServeCount();
        this.rewardMaxCount = reward.getMaxCount();
        this.rewardDate = reward.getRewardDate();
        this.rewardFee = reward.getRewardFee();
        this.rewardFeeFar = reward.getRewardFeeFar();
        this.rewardContent = reward.getContent();
        this.rewardPrice = reward.getPrice();
    }
}
