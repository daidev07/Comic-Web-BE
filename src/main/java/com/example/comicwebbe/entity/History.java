package com.example.comicwebbe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table (name = "lichsu")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnoreProperties("user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_truyen")
    @JsonIgnoreProperties("story")
    private Story story;

    @ManyToOne
    @JoinColumn(name = "id_chuong")
    @JsonIgnoreProperties("chapter")
    private Chapter chapter;

    private LocalDateTime lan_cuoi_doc;


    public History(User user, Story story, Chapter chapter, LocalDateTime lan_cuoi_doc) {
        this.user = user;
        this.story = story;
        this.chapter = chapter;
        this.lan_cuoi_doc = lan_cuoi_doc;
    }
    public History() {
    }
}
