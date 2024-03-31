package com.example.comicwebbe.repository;

import com.example.comicwebbe.entity.Chuong;
import com.example.comicwebbe.entity.TheLoai;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheLoaiRepository extends CrudRepository<TheLoai, Long> {

    List<TheLoai> findAll();
    Optional<TheLoai> findById(Long id);
    void deleteById(Long id);
}
