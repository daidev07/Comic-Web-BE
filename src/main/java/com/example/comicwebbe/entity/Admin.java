package com.example.comicwebbe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean is_admin;

}
