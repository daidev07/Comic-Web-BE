package com.example.comicwebbe.dto;

import lombok.Data;

@Data
public class AddLikeCommentRequest {
    private Long id_user;
    private Long id_binhluan;
    private Boolean thich;
}
