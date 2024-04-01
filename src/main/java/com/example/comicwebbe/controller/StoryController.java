package com.example.comicwebbe.controller;
import com.example.comicwebbe.dto.UpdateStoryRequest;
import com.example.comicwebbe.dto.AddStoryRequest;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/home")
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

    @PostMapping("/add")
    public ResponseEntity<String> addStory(@RequestBody AddStoryRequest addStoryRequest){
        try{
            storyService.addStory(addStoryRequest);
            return ResponseEntity.ok("Thêm thành công");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateStory(@RequestBody UpdateStoryRequest updateStoryRequest){
        try{
            storyService.updateStory(updateStoryRequest);
            return ResponseEntity.ok("Sửa thành công");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteTran(@PathVariable Long id){
        try {
            storyService.deleteById(id);
            return ResponseEntity.ok("Xóa thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



}
