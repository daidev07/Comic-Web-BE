package com.example.comicwebbe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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

    public Chapter(Long so, String ten, String noidung, Story story) {
        this.so = so;
        this.ten = ten;
        this.noidung = noidung;
        this.story = story;
    }
    public Chapter() {
    }
}
