package com.example.comicwebbe.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
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

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}