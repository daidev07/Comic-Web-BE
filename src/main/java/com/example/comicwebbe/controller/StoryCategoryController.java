package com.example.comicwebbe.controller;

import com.example.comicwebbe.dto.AddCategoryRequest;
import com.example.comicwebbe.entity.Category;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.service.StoryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/story-category")
public class StoryCategoryController {
    @Autowired
    private StoryCategoryService storyCategoryService;



    @GetMapping("/{categoryId}/story")
    public ResponseEntity<List<Story>> getDetailCate(@PathVariable Long categoryId) {
        try {
            List<Story> story = storyCategoryService.getListStoryByCategoryId(categoryId);
            return ResponseEntity.ok(story);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}