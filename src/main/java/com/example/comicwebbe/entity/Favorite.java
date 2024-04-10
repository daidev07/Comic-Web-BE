package com.example.comicwebbe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "yeuthich")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_truyen")
    private Story story;

    public Favorite(User user, Story story) {
        this.user = user;
        this.story = story;
    }

    public Favorite() {

    }
}
