package com.example.comicwebbe.repository;
import com.example.comicwebbe.entity.StoryCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryCategoryRepository extends CrudRepository<StoryCategory, Long> {
    List<StoryCategory> findAll();
    Optional<StoryCategory> findById(Long id);
    void deleteById(Long id);
}

