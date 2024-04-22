package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c  WHERE c.story.id = :storyId")
    List<Comment> findListCommentsByStoryId(Long storyId);

    @Modifying
    @Query("DELETE FROM Comment c WHERE c.story.id = :storyId")
    void deleteCommentsByStoryId(Long storyId);

    @Modifying
    @Query("DELETE FROM Comment c WHERE c.id = :commentId")
    void deleteCommentByCommentId(Long commentId);
}

