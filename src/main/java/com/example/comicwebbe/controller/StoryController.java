package com.example.comicwebbe.controller;
import com.example.comicwebbe.dto.UpdateStoryRequest;
import com.example.comicwebbe.dto.AddStoryRequest;
import com.example.comicwebbe.entity.Category;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.service.StoryCategoryService;
import com.example.comicwebbe.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/admin")
public class StoryController {
    @Autowired
    private StoryService storyService;
    @Autowired
    private StoryCategoryService storyCategoryService;

    @GetMapping("")
    public ResponseEntity<List<Story>> getAllTruyen(){
        try{
            List<Story> list = storyService.getAllTruyen();
                return ResponseEntity.ok(list);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<String> addStory(@RequestBody AddStoryRequest addStoryRequest){
        try{
            storyService.addStory(addStoryRequest);
            return ResponseEntity.ok("Thêm thành công");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("/update/{storyId}") // Sử dụng path variable để lấy storyId từ URL
    public ResponseEntity<String> updateStory(@PathVariable Long storyId, @RequestBody UpdateStoryRequest updateStoryRequest){
        try{
            storyService.updateStory(storyId, updateStoryRequest); // Truyền storyId và updateStoryRequest vào service
            return ResponseEntity.ok("Sửa thành công");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi không xác định");
        }
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteStoryAndRelatedCategories(@PathVariable Long id) {
        try {
            storyService.deleteStoryAndRelatedCategories(id);
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
