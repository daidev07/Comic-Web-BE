package com.example.comicwebbe.entity;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
@Table (name = "truyen")
public class Truyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ten;
    @Lob
    private Blob avt;
    private String gioithieu;
    private String tacgia;
    private Integer view;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }


    public String getGioithieu() {
        return gioithieu;
    }

    public void setGioithieu(String gioithieu) {
        this.gioithieu = gioithieu;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public Integer getView() {
        return view;
    }


    public void setView(Integer view) {
        this.view = view;
    }

    public void setAvt(Blob avt) {
        this.avt = avt;
    }
    public Blob getAvt() {
        return avt;
    }
}
