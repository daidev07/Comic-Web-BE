package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.Slide;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideRepository extends CrudRepository<Slide, Long> {
    List<Slide> findAll();

    @Modifying
    @Query("DELETE FROM Slide s WHERE s.story.id = :storyId")
    void deleteSlideByStoryId(Long storyId);
}
