package com.example.comicwebbe.service;

import com.example.comicwebbe.dto.AddCategoryRequest;
import com.example.comicwebbe.dto.AddStoryRequest;
import com.example.comicwebbe.entity.Category;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.entity.StoryCategory;
import com.example.comicwebbe.repository.CategoryRepository;
import com.example.comicwebbe.repository.StoryCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private StoryCategoryRepository storyCategoryRepository;

    public List<Category> getAllTheLoai() {
        return categoryRepository.findAll();
    }

    @Transactional
    public void deleteById(Long cateId) {
        storyCategoryRepository.deleteCateFromStoryCategoryByCateId(cateId);
        categoryRepository.deleteById(cateId);
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public void addCate(AddCategoryRequest addCategoryRequest) {

        Category category = new Category();
        category.setTen(addCategoryRequest.getTen());

        Category newCate = categoryRepository.save(category);
    }
}
