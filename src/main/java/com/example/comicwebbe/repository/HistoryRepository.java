package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {
    List<History> findAll();
    Optional<History> findById(Long id);
    void deleteById(Long id);
}
