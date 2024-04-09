package com.example.comicwebbe.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.attoparser.dom.Text;

import java.awt.*;

@Entity
@Data
@Table (name = "truyen")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ten;
    private String avt;
    private String gioithieu;
    private String tacgia;
    private Integer view;
    public Story(Long id) {
        this.id = id;
    }
    public Story() {}
}
