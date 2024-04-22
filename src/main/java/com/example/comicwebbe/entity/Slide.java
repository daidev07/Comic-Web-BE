package com.example.comicwebbe.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
@Table(name = "slide")
public class Slide {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_truyen", nullable = false)
    private Story story;

    public Slide(Long storyId) {
        this.story = new Story(storyId);
    }
    public Slide() {

    }

}