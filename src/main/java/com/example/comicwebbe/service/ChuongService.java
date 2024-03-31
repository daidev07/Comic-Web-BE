package com.example.comicwebbe.service;
import com.example.comicwebbe.entity.Chuong;
import com.example.comicwebbe.repository.BinhLuanRepository;
import com.example.comicwebbe.repository.ChuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChuongService {
    @Autowired
    private ChuongRepository chuongRepository;
    public List<Chuong> getAllChuong() {
        return chuongRepository.findAll();
    }
    public void deleteById(Long id){
        chuongRepository.deleteById(id);
    }
    public void findById(Long id){
        chuongRepository.findById(id);
    }
}
