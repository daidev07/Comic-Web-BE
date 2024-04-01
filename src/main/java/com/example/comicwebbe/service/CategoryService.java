package com.example.comicwebbe.service;
import com.example.comicwebbe.entity.Category;
import com.example.comicwebbe.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> getAllTheLoai() {
        return categoryRepository.findAll();
    }
    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }
    public void findById(Long id){

        categoryRepository.findById(id);
    }
}
