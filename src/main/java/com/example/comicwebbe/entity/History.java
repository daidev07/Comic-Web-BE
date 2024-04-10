package com.example.comicwebbe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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

    @ManyToOne
    @JoinColumn(name = "id_chuong")
    private Chapter chapter;

    private boolean da_doc;
}
