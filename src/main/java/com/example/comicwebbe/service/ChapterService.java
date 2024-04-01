package com.example.comicwebbe.service;
import com.example.comicwebbe.entity.Chapter;
import com.example.comicwebbe.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;
    public List<Chapter> getAllChuong() {
        return chapterRepository.findAll();
    }
    public void deleteById(Long id){
        chapterRepository.deleteById(id);
    }
    public void findById(Long id){
        chapterRepository.findById(id);
    }
}
