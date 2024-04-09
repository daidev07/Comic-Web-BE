package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findById(Long id);
    void deleteById(Long id);

    @Query("SELECT c FROM Comment c  WHERE c.story.id = :storyId")
    List<Comment> findListByStoryId(Long storyId);
}

