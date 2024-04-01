package com.example.comicwebbe.dto;

import com.example.comicwebbe.entity.Category;
import lombok.Data;
import org.attoparser.dom.Text;

import java.awt.*;
import java.sql.Blob;

@Data
public class UpdateStoryRequest {
    private Long id;
    private String ten;
    private String avt;
    private String gioithieu;
    private String tacgia;
    private Category id_theloai;
    private Integer view;
}
