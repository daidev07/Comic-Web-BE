package com.example.comicwebbe.dto;

import com.example.comicwebbe.entity.Story;
import lombok.Data;

@Data
public class UpdateChapterRequest {
    private Long id;
    private Long so;
    private String ten;
    private String noidung;
    private Boolean is_reading;
    private Story story;
}
