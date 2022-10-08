package com.steady.leisurethatapi.project.manage.dto;

import com.steady.leisurethatapi.database.entity.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryResponseDTO {
    private int storyId;
    private String storyTitle;
    private String storyContent;
    private Attachment storyAttachment;
}
