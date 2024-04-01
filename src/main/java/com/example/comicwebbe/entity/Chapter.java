package com.example.comicwebbe.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "chuong")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long so;
    private String ten;
    private String noidung;
    private Boolean is_reading;
    @ManyToOne
    @JoinColumn(name = "id_truyen")
    private Story story;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSo() {
        return so;
    }

    public void setSo(Long so) {
        this.so = so;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Boolean getIs_reading() {
        return is_reading;
    }

    public void setIs_reading(Boolean is_reading) {
        this.is_reading = is_reading;
    }

    public Story getTruyen() {
        return story;
    }

    public void setTruyen(Story story) {
        this.story = story;
    }
}