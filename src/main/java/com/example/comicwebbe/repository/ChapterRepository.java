package com.example.comicwebbe.repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.comicwebbe.entity.Chapter;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends CrudRepository<Chapter, Long> {
    @Query("SELECT c FROM Chapter c WHERE c.story.id = :storyId")
    List<Chapter> findListChaptersByStoryId(@Param("storyId") Long storyid);
    @Modifying
    @Query("DELETE FROM Chapter c WHERE c.id = :chapterId")
    void deleteChapterByChapterId(Long chapterId);

    @Modifying
    @Query("DELETE FROM Chapter c WHERE c.story.id = :storyId ")
    void deleteChaptersByStoryId(Long storyId);

}
