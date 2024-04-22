package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.Favorite;
import com.example.comicwebbe.entity.Story;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Long> {
    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId AND f.story.id = :storyId")
    Optional<Favorite> findOneByUserIdAndStoryId(Long userId, Long storyId);

    @Modifying
    @Query("DELETE FROM Favorite f WHERE f.user.id = :userId AND f.story.id = :storyId")
    void deleteFavoriteByUserIdAndStoryId(Long userId, Long storyId);

    @Query("SELECT c FROM Story c INNER JOIN Favorite f ON c.id = f.story.id WHERE f.user.id = :userId")
    List<Story> findListFavoriteStoryByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM Favorite c WHERE c.story.id = :storyId")
    void deleteFavoritesByStoryId(Long storyId);
}
