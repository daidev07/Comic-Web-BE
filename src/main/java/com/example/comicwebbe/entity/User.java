package com.example.comicwebbe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;

@Entity
@Data
@Table (name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hoten;
    private String email;
    private String username;
    private String password;
    private Boolean is_admin;
    private String avt;

    public User(Long id) {
        this.id = id;
    }

    public User() {
    }

    public User(String hoten) {
        this.hoten = hoten;
    }

}
