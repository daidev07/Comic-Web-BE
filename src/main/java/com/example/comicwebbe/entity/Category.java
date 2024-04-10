package com.example.comicwebbe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "theloai")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ten;

    public Category(Long id) {
        this.id = id;
    }

    public Category() {}

}
