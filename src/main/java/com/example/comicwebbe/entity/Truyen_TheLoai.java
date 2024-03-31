package com.example.comicwebbe.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "truyen_theloai")
public class Truyen_TheLoai {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_truyen", nullable = false)
    private Truyen truyen;

    @ManyToOne
    @JoinColumn(name = "id_theloai", nullable = false)
    private TheLoai theLoai;


    public Truyen getTruyen() {
        return truyen;
    }

    public void setTruyen(Truyen truyen) {
        this.truyen = truyen;
    }

    public TheLoai getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(TheLoai theLoai) {
        this.theLoai = theLoai;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}