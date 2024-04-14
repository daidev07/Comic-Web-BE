package com.example.comicwebbe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime thoi_gian_dang;
    private Integer view;
    @ManyToOne
    @JoinColumn(name = "id_truyen")
    private Story story;

    public Chapter(Long so, String ten, String noidung, Story story, LocalDateTime thoi_gian_dang, Integer view) {
        this.so = so;
        this.ten = ten;
        this.noidung = noidung;
        this.story = story;
        this.thoi_gian_dang = thoi_gian_dang;
        this.view = view;
    }
    public Chapter() {
    }

    public Chapter(Long chapterId) {
    }

}
