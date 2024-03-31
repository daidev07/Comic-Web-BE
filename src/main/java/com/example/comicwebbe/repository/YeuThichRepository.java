package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.Chuong;
import com.example.comicwebbe.entity.YeuThich;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface YeuThichRepository extends CrudRepository<YeuThich, Long> {
    List<YeuThich> findAll();
    Optional<YeuThich> findById(Long id);
    void deleteById(Long id);
}
