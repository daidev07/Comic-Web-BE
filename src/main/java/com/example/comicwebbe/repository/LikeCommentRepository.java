package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.LikeComment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeCommentRepository extends CrudRepository<LikeComment, Long> {

    @Query("SELECT c FROM LikeComment c WHERE c.user.id = :userId AND c.comment.id = :commentId")
    Optional<LikeComment> getLikeByUserIdAndCommentId(Long userId, Long commentId);

    @Modifying
    @Query("DELETE FROM LikeComment c WHERE c.user.id = :userId AND c.comment.id = :commentId")
    void deleteLikeByUserIdAndCommentId(Long userId, Long commentId);

    @Modifying
    @Query("DELETE FROM LikeComment c WHERE c.comment.id = :commentId")
    void deleteListByCommentId(Long commentId);
}
