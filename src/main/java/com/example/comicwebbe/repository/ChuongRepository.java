package com.example.comicwebbe.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.comicwebbe.entity.Chuong;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChuongRepository extends CrudRepository<Chuong, Long> {
    List<Chuong> findAll();
    Optional<Chuong> findById(Long id);
    void deleteById(Long id);
}
