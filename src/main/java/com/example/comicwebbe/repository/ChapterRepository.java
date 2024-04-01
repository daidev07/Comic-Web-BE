package com.example.comicwebbe.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.comicwebbe.entity.Chapter;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends CrudRepository<Chapter, Long> {
    List<Chapter> findAll();
    Optional<Chapter> findById(Long id);
    void deleteById(Long id);
}
