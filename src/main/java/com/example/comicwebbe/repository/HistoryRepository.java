package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.Chapter;
import com.example.comicwebbe.entity.History;
import com.example.comicwebbe.entity.Story;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {

    List<History> findAll();
    @Query("SELECT c FROM History c WHERE c.user.id = :userId")
    List<History> findListHistoriesByUserId(@Param("userId") Long userId);

    @Query("SELECT h.story FROM History h WHERE h.user.id = :userId")
    List<Story> getListReadStoryByUserId(Long userId);

    @Query("SELECT h.chapter FROM History h WHERE h.user.id = :userId AND h.story.id = :storyId")
    List<Chapter>  getListReadChapterByUserIdAndStoryId(Long userId, Long storyId);

    @Query("SELECT h FROM History h WHERE h.user.id = :userId AND h.story.id = :storyId AND h.chapter.id = :chapterId")
    List<History> findByUserIdAndStoryIdAndChapterId(Long userId, Long storyId, Long chapterId);
    @Modifying
    @Query("DELETE FROM History c WHERE c.story.id = :storyId")
    void deleteHistoriesByStoryId(Long storyId);
}
