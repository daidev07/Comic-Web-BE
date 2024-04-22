package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.Category;
import com.example.comicwebbe.entity.Chapter;
import com.example.comicwebbe.entity.History;
import com.example.comicwebbe.entity.Story;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepository extends CrudRepository<Story, Long> {
    List<Story> findAll();
    Optional<Story> findById(Long id);
    void deleteById(Long id);

}
