package com.example.comicwebbe.controller;
import com.example.comicwebbe.dto.AddChapterRequest;
import com.example.comicwebbe.dto.AddSlideRequest;
import com.example.comicwebbe.entity.Slide;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/slide")
public class SlideController {
    @Autowired
    private SlideService slideService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Slide>> getAllSlides(){
        try{
            List<Slide> list = slideService.getAllSlides();
            return ResponseEntity.ok(list);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/add/{storyId}")
    public ResponseEntity<String> addSlide(@PathVariable Long storyId, @ModelAttribute AddSlideRequest addSlideRequest) {
        try {
            slideService.addSlide(storyId, addSlideRequest);
            return ResponseEntity.ok("Thêm slide thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi không xác định");
        }
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteSlide(@PathVariable Long id) {
        try {
            slideService.deleteSlide(id);
            return ResponseEntity.ok("Xóa thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
