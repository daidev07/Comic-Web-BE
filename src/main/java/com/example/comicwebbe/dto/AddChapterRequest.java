package com.example.comicwebbe.dto;
import com.example.comicwebbe.entity.Story;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class AddChapterRequest {
    private Long so;
    private String ten;
    private MultipartFile noidung;
    private Long id_truyen;
    private Integer view;
}
