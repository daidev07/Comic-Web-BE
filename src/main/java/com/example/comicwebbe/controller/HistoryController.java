package com.example.comicwebbe.controller;

import com.example.comicwebbe.dto.UpdateChapterRequest;
import com.example.comicwebbe.dto.UpdateUserHistoryRequest;
import com.example.comicwebbe.dto.UserFavoriteRequest;
import com.example.comicwebbe.dto.UserHistoryRequest;
import com.example.comicwebbe.entity.Chapter;
import com.example.comicwebbe.entity.Comment;
import com.example.comicwebbe.entity.History;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getListReadStoryByUserId(@PathVariable Long userId) {
        try {
            List<Story> list = historyService.getListReadStoryByUserId(userId);
            if (!list.isEmpty()) {
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bạn chưa đọc bộ truyện nào!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<History>> findListHistoriesByUserId(@PathVariable Long userId) {
        List<History> histories = historyService.findListHistoriesByUserId(userId);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping("/get/{userId}/{storyId}")
    public ResponseEntity<List<Chapter>> getListReadChapterByUserIdAndStoryId(@PathVariable Long userId, @PathVariable Long storyId){
        try{
            List<Chapter> list = historyService.getListReadChapterByUserIdAndStoryId(userId, storyId);
            if(!list.isEmpty()){
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add/{userId}/{storyId}/{chapterId}")
    public ResponseEntity<String> addOrUpdateHistory(@PathVariable Long userId, @PathVariable Long storyId, @PathVariable Long chapterId){
        try{
            historyService.addOrUpdateHistory(userId, storyId, chapterId);
            return ResponseEntity.ok("Thêm hoặc cập nhật lịch sử thành công");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi không xác định");
        }
    }

}
