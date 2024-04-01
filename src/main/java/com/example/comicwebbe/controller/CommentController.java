package com.example.comicwebbe.controller;
import com.example.comicwebbe.entity.Comment;
import com.example.comicwebbe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @GetMapping("/{storyId}")
    public ResponseEntity<Optional<Comment>> getListBinhLuanByStoryId(@PathVariable Long storyId) {
        Optional<Comment> comments = commentService.getListBinhLuanByStoryId(storyId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
