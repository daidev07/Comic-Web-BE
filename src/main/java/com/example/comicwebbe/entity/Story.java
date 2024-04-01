package com.example.comicwebbe.entity;

import jakarta.persistence.*;
import org.attoparser.dom.Text;

import java.awt.*;

@Entity
@Table (name = "truyen")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ten;
    @Lob
    private byte[] avt;
    private String gioithieu;
    private String tacgia;
    private Integer view;

    public Story(Long id) {
        this.id = id;
    }

    public Story() {

    }

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

    public byte[] getAvt() {
        return avt;
    }

    public void setAvt(byte[] avt) {
        this.avt = avt;
    }
}
