package com.example.comicwebbe.controller;

import com.example.comicwebbe.dto.AddChapterRequest;
import com.example.comicwebbe.entity.Chapter;
import com.example.comicwebbe.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @GetMapping("")
    public ResponseEntity<List<Chapter>> getAllChuong(){
        try{
            List<Chapter> list = chapterService.getAllChuong();
            if(!list.isEmpty()){
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/{storyId}/add")
    public ResponseEntity<String> addChapterToStory(@PathVariable Long storyId, @RequestBody AddChapterRequest addChapterRequest) {
        try {
            chapterService.addChapter(storyId, addChapterRequest);
            return ResponseEntity.ok("Thêm chương thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi không xác định");
        }
    }


}
