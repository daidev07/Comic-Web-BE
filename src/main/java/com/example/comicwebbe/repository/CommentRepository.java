package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findById(Long id);
    void deleteById(Long id);
}

