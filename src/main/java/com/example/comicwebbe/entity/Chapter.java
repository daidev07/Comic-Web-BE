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
    @Lob
    private byte[] noidung;
    private Boolean is_reading;
    @ManyToOne
    @JoinColumn(name = "id_truyen")
    private Story story;

}
