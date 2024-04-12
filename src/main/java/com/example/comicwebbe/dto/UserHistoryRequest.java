package com.example.comicwebbe.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserHistoryRequest {
    private Long userId;
    private Long storyId;
    private Long chapterId;
    private LocalDateTime lan_cuoi_doc;
}
