package com.example.comicwebbe.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "binhluan")
public class BinhLuan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User id_user;
    @ManyToOne
    @JoinColumn(name = "id_truyen")
    private Truyen id_truyen;
    private String noidung;

    private LocalDate thoi_gian_dang;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public Truyen getId_truyen() {
        return id_truyen;
    }

    public void setId_truyen(Truyen id_truyen) {
        this.id_truyen = id_truyen;
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
