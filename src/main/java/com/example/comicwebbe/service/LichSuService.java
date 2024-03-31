package com.example.comicwebbe.service;

import com.example.comicwebbe.entity.Chuong;
import com.example.comicwebbe.entity.LichSu;
import com.example.comicwebbe.repository.ChuongRepository;
import com.example.comicwebbe.repository.LichSuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LichSuService {
    @Autowired
    private LichSuRepository lichSuRepository;
    public List<LichSu> getAllLichSu() {
        return lichSuRepository.findAll();
    }
    public void deleteById(Long id){
        lichSuRepository.deleteById(id);
    }
    public void findById(Long id) {
        lichSuRepository.findById(id);
    }
}
