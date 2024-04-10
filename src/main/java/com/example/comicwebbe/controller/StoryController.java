package com.example.comicwebbe.controller;

import com.example.comicwebbe.dto.AddStoryRequest;
import com.example.comicwebbe.dto.UpdateStoryRequest;
import com.example.comicwebbe.entity.Category;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.service.ChapterService;
import com.example.comicwebbe.service.StoryCategoryService;
import com.example.comicwebbe.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/story")
public class StoryController {
    @Autowired
    private StoryService storyService;

    @GetMapping("")
    public ResponseEntity<List<Story>> getAllTruyen(){
        try{
            List<Story> list = storyService.getAllTruyen();
            return ResponseEntity.ok(list);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Story>> getDetailStory(@PathVariable Long id){
        try{
            Optional<Story> story = storyService.findById(id);
            return ResponseEntity.ok(story);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStory(@ModelAttribute AddStoryRequest addStoryRequest){
        try{
            storyService.addStory(addStoryRequest);
            return ResponseEntity.ok("Thêm thành công");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("/update/{storyId}")
    public ResponseEntity<String> updateStory(@PathVariable Long storyId, @ModelAttribute UpdateStoryRequest updateStoryRequest){
        try{
            storyService.updateStory(storyId, updateStoryRequest);
            return ResponseEntity.ok("Sửa thành công");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi không xác định");
        }
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteStory(@PathVariable Long id) {
        try {
            storyService.deleteStory(id);
            return ResponseEntity.ok("Xóa thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //LẤY TẤT CẢ THỂ LOẠI THUỘC ID TRUYỆN ĐƯỢC CHỌN
    @GetMapping("/{storyId}/categories")
    public ResponseEntity<List<Category>> getStoryCategories(@PathVariable Long storyId) {
        try {
            List<Category> categories = storyService.getCategoriesForStory(storyId);
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
