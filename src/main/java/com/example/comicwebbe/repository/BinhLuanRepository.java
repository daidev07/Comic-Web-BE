package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.BinhLuan;
import com.example.comicwebbe.entity.LichSu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BinhLuanRepository extends CrudRepository<BinhLuan, Long> {
    Optional<BinhLuan> findById(Long id);
    void deleteById(Long id);
}

