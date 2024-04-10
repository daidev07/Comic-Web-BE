package com.example.comicwebbe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table (name = "binhluan")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_truyen")
    private Story story;
    private String noidung;
    private LocalDateTime thoi_gian_dang;

    public Comment(User user, Story story, String noidung, LocalDateTime thoi_gian_dang) {
        this.user = user;
        this.story = story;
        this.noidung = noidung;
        this.thoi_gian_dang = thoi_gian_dang;
    }

    public Comment() {
    }
}
