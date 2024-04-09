package com.example.comicwebbe.dto;

import lombok.Data;

@Data
public class UserFavoriteRequest {
    private Long id_user;
    private Long id_story;
}
