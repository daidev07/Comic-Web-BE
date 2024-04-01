package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.Favorite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Long> {
    List<Favorite> findAll();
    Optional<Favorite> findById(Long id);
    void deleteById(Long id);
}
