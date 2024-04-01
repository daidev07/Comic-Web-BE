package com.example.comicwebbe.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "lichsu")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_truyen")
    private Story story;

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
}
