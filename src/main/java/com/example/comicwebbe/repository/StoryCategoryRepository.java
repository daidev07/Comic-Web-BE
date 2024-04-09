package com.example.comicwebbe.repository;
import com.example.comicwebbe.entity.Category;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.entity.StoryCategory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryCategoryRepository extends CrudRepository<StoryCategory, Long> {
    List<StoryCategory> findAll();
    Optional<StoryCategory> findById(Long id);
    void deleteById(Long id);
    @Modifying
    @Query("DELETE FROM StoryCategory sc WHERE sc.story.id = :storyId")
    void deleteStoryCategoryByStoryId(Long storyId);
    @Modifying
    @Query("DELETE FROM StoryCategory sc WHERE sc.category.id = :cateId")
    void deleteCateFromStoryCategoryByCateId(Long cateId);
    @Query("SELECT c FROM Category c INNER JOIN StoryCategory sc ON c.id = sc.category.id WHERE sc.story.id = :storyId")
    List<Category> findCategoriesByStoryId(Long storyId);

    @Query("SELECT c FROM Story c INNER JOIN StoryCategory sc ON c.id = sc.story.id WHERE sc.category.id = :cateforyId")
    List<Story> findListStoryByCategoryId(Long cateforyId);
}

