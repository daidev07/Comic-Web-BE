package com.example.comicwebbe.service;

import com.example.comicwebbe.entity.Category;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.entity.StoryCategory;
import com.example.comicwebbe.repository.StoryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoryCategoryService {
    @Autowired
    private StoryCategoryRepository storyCategoryRepository;

    public List<Story> getListStoryByCategoryId(Long categoryId) {
        return storyCategoryRepository.findListStoryByCategoryId(categoryId);
    }

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
