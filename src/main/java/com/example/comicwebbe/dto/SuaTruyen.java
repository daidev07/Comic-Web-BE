package com.example.comicwebbe.dto;

import com.example.comicwebbe.entity.TheLoai;
import lombok.Data;

import java.sql.Blob;

@Data
public class SuaTruyen {
    private Long id;
    private String ten;
    private Blob avt;
    private String gioithieu;
    private String tacgia;
    private TheLoai id_theloai;
    private Integer view;
}
