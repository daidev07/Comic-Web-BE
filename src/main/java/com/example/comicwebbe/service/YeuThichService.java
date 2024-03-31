package com.example.comicwebbe.service;

import com.example.comicwebbe.entity.User;
import com.example.comicwebbe.entity.YeuThich;
import com.example.comicwebbe.repository.UserRepository;
import com.example.comicwebbe.repository.YeuThichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YeuThichService {
    @Autowired
    private YeuThichRepository yeuThichRepository;
    public List<YeuThich> getAllYeuThich() {
        return yeuThichRepository.findAll();
    }
    public void deleteById(Long id){
        yeuThichRepository.deleteById(id);
    }
    public void findById(Long id) {
        yeuThichRepository.findById(id);
    }
}
