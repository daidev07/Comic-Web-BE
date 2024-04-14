package com.example.comicwebbe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table (name = "truyen")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ten;
    private String avt;
    private String gioithieu;
    private String tacgia;
    private LocalDateTime thoi_gian_dang;
    private Integer view;

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("story")
    private List<Chapter> chapters;


    public Story(String ten, String avt, String gioithieu, String tacgia, LocalDateTime thoi_gian_dang) {
        this.ten = ten;
        this.avt = avt;
        this.gioithieu = gioithieu;
        this.tacgia = tacgia;
        this.thoi_gian_dang = thoi_gian_dang;
    }

    public Story(Long id) {
        this.id = id;
    }
    public Story() {}


}
