package com.example.comicwebbe.dto;

import lombok.Data;
import org.attoparser.dom.Text;

import java.awt.*;
import java.util.List;

@Data
public class AddStoryRequest {
    private String ten;
    private String avt;
    private String gioithieu;
    private String tacgia;
    private Integer view;
    private List<Long> idTheLoais;
    private List<AddChapterRequest> addChapterRequests;
}
