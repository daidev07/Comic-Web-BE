package com.example.comicwebbe.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
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

    public Comment(User user, Story story, String noidung, LocalDate thoi_gian_dang) {
        this.user = user;
        this.story = story;
        this.noidung = noidung;
        this.thoi_gian_dang = thoi_gian_dang;
    }

    public Comment() {
    }

    private LocalDate thoi_gian_dang;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Story getTruyen() {
        return story;
    }

    public void setTruyen(Story story) {
        this.story = story;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public LocalDate getThoi_gian_dang() {
        return thoi_gian_dang;
    }

    public void setThoi_gian_dang(LocalDate thoi_gian_dang) {
        this.thoi_gian_dang = thoi_gian_dang;
    }
}
