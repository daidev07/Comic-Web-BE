package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAll();
    Optional<Category> findById(Long id);
    void deleteById(Long id);
}
