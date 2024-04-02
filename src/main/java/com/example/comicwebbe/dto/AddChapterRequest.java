package com.example.comicwebbe.dto;
import com.example.comicwebbe.entity.Story;
import lombok.Data;

import java.util.List;

@Data
public class AddChapterRequest {
    private Long id;
    private Long so;
    private String ten;
    private String noidung;
    private Boolean is_reading;
    private Long id_truyen;
}
