package com.example.comicwebbe.dto;

import com.example.comicwebbe.entity.TheLoai;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;

@Data
public class ThemTruyen {
    private Long id;
    private String ten;
    private Blob avt;
    private String gioithieu;
    private String tacgia;
    private Integer view;
}
