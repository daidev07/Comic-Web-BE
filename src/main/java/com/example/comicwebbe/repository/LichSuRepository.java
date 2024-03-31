package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.LichSu;
import com.example.comicwebbe.entity.YeuThich;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LichSuRepository extends CrudRepository<LichSu, Long> {
    List<LichSu> findAll();
    Optional<LichSu> findById(Long id);
    void deleteById(Long id);
}
