package com.example.comicwebbe.service;
import com.example.comicwebbe.entity.BinhLuan;
import com.example.comicwebbe.repository.BinhLuanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BinhLuanService {
    @Autowired
    private BinhLuanRepository binhLuanRepository;
    public Optional<BinhLuan> getListBinhLuanByStoryId(Long storyId) {
        return binhLuanRepository.findById(storyId);
    }
    public void deleteById(Long id){
        binhLuanRepository.deleteById(id);
    }
    public void findById(Long id){
        binhLuanRepository.findById(id);
    }
}
