package com.example.comicwebbe.controller;
import com.example.comicwebbe.dto.AddCommentRequest;
import com.example.comicwebbe.dto.AddStoryRequest;
import com.example.comicwebbe.entity.Comment;
import com.example.comicwebbe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @GetMapping("/{storyId}")
    public ResponseEntity<List<Comment>> getListBinhLuanByStoryId(@PathVariable Long storyId) {
        List<Comment> comments = commentService.getListBinhLuanByStoryId(storyId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addComment(@RequestBody AddCommentRequest addCommentRequest){
        try{
            commentService.addComment(addCommentRequest);
            return ResponseEntity.ok("Thêm thành công");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
