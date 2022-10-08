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
    private AccountInfoResponseDTO accountInfoResponseDTO;
    private BusinessInfoResponseDTO businessInfoResponseDTO;
    private MemberResponseDTO memberResponseDTO;
    private List<ProductResponseDTO> productResponseDTOList;
    private ProjectResponseDTO projectResponseDTO;
    private List<RewardResponseDTO> rewardResponseDTOList;
    private List<StoryResponseDTO> storyResponseDTOList;

    public ProjectDetailResponseDTO(Project project, List<Story> story, List<Product> product, List<Reward> reward){

    }
}
