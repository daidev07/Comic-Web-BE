package com.example.comicwebbe.dto;

import lombok.Data;
import org.attoparser.dom.Text;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@Data
public class AddStoryRequest {
    private String ten;
    private MultipartFile avtFile;
    private String gioithieu;
    private String tacgia;
    private Integer view;
    private List<Long> idTheLoais;
    private List<AddChapterRequest> addChapterRequests;
}
