package com.example.comicwebbe.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
@Table(name = "truyen_theloai")
public class StoryCategory {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_truyen", nullable = false)
    private Story story;

    @ManyToOne
    @JoinColumn(name = "id_theloai", nullable = false)
    private Category category;

    public StoryCategory(Long idStory, Long idCategory) {
        this.story = new Story(idStory);
        this.category = new Category(idCategory);
    }
    public StoryCategory() {

    }
}