package com.example.comicwebbe.service;
import com.example.comicwebbe.entity.TheLoai;
import com.example.comicwebbe.repository.TheLoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheLoaiService {
    @Autowired
    private TheLoaiRepository theLoaiRepository;
    public List<TheLoai> getAllTheLoai() {
        return theLoaiRepository.findAll();
    }
    public void deleteById(Long id){
        theLoaiRepository.deleteById(id);
    }
    public void findById(Long id){

        theLoaiRepository.findById(id);
    }
}
