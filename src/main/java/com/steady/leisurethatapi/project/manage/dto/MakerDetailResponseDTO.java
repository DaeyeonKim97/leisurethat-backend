package com.steady.leisurethatapi.project.manage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakerDetailResponseDTO {
    private MemberResponseDTO member;
    private List<ProjectResponseDTO> projectList;
}
