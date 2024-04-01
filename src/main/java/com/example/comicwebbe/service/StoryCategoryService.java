package com.example.comicwebbe.service;

import com.example.comicwebbe.entity.StoryCategory;
import com.example.comicwebbe.repository.StoryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryCategoryService {
    @Autowired
    private StoryCategoryRepository storyCategoryRepository;

    public List<StoryCategory> getAllStoryCategory() {
        return storyCategoryRepository.findAll();
    }
    public void deleteById(Long id){
        storyCategoryRepository.deleteById(id);
    }
    public void findById(Long id) {
        storyCategoryRepository.findById(id);
    }
}
