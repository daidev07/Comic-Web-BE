package com.example.comicwebbe.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "yeuthich")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User id_user;
    @ManyToOne
    @JoinColumn(name = "id_truyen")
    private Story id_story;

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

    public Story getId_truyen() {
        return id_story;
    }

    public void setId_truyen(Story id_story) {
        this.id_story = id_story;
    }
}
