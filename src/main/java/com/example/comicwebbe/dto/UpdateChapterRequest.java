package com.example.comicwebbe.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateChapterRequest {
    private Long so;
    private String ten;
    private MultipartFile noidung;
    private Long id_truyen;
}
