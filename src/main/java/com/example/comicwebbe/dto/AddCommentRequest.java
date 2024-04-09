package com.example.comicwebbe.dto;

import lombok.Data;

@Data
public class AddCommentRequest {
    private Long userId;
    private Long storyId;
    private String noidung;
}
