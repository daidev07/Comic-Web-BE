package com.example.comicwebbe.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddCommentRequest {
    private Long userId;
    private Long storyId;
    private String noidung;
    private LocalDateTime thoi_gian_dang;
}
