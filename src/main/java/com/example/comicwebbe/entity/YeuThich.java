package com.example.comicwebbe.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "yeuthich")
public class YeuThich {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User id_user;
    @ManyToOne
    @JoinColumn(name = "id_truyen")
    private Truyen id_truyen;

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
}
