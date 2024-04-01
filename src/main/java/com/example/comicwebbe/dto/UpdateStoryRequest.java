package com.example.comicwebbe.dto;

import com.example.comicwebbe.entity.Category;
import lombok.Data;
import org.attoparser.dom.Text;

import java.awt.*;
import java.sql.Blob;
import java.util.List;

@Data
public class UpdateStoryRequest {
    private Long id;
    private String ten;
    private String avt;
    private String gioithieu;
    private String tacgia;
    private Integer view;
    private List<Long> idTheLoais;
}
