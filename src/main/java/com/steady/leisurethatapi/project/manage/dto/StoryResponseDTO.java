package com.steady.leisurethatapi.project.manage.dto;

import com.steady.leisurethatapi.database.entity.Attachment;
import com.steady.leisurethatapi.database.entity.Story;
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

    public StoryResponseDTO(Story story){
        this.storyId = story.getId();
        this.storyTitle = story.getTitle();
        this.storyContent = story.getContent();
        this.storyAttachment = story.getAttachment();
    }
}
