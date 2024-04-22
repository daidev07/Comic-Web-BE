package com.example.comicwebbe.controller;

import com.example.comicwebbe.dto.AddLikeCommentRequest;
import com.example.comicwebbe.entity.LikeComment;
import com.example.comicwebbe.service.LikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("api/checklikecomment")
public class LikeCommentController {

    @Autowired
    private LikeCommentService likeCommentService;

    @PostMapping("/add")
    public ResponseEntity<String> addLike(@RequestBody AddLikeCommentRequest addLikeCommentRequest){
        try{
            likeCommentService.addLike(addLikeCommentRequest);
            return ResponseEntity.ok("Add Like Success");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi không xác định");
        }
    }

    @GetMapping("/get/{userId}/{commentId}")
    public ResponseEntity<Boolean> getLikeByUserIdAndCommentId(@PathVariable Long userId, @PathVariable Long commentId){
        try {
            Optional<LikeComment> checkLikeOptional = likeCommentService.getLikeByUserIdAndCommentId(userId, commentId);
            return ResponseEntity.ok(checkLikeOptional.isPresent());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/remove/{userId}/{commentId}")
    public ResponseEntity<String> deleteLikeByUserIdAndCommentId(@PathVariable Long userId, @PathVariable Long commentId){
        try{
            likeCommentService.deleteLikeByUserIdAndCommentId(userId, commentId);
            return ResponseEntity.ok("Deleted Success");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Delete LikeComment: " + e.getMessage());
        }
    }

}
