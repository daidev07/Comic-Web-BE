package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.Truyen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TruyenRepository extends CrudRepository<Truyen, Long> {

    List<Truyen> findAll();
    Optional<Truyen> findById(Long id);
    void deleteById(Long id);
}
