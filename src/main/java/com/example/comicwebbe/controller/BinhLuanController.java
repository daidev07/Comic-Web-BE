package com.example.comicwebbe.controller;
import com.example.comicwebbe.entity.BinhLuan;
import com.example.comicwebbe.service.BinhLuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/comment")
public class BinhLuanController {

    @Autowired
    private BinhLuanService binhLuanService;
    @GetMapping("/{storyId}")
    public ResponseEntity<Optional<BinhLuan>> getListBinhLuanByStoryId(@PathVariable Long storyId) {
        Optional<BinhLuan> comments = binhLuanService.getListBinhLuanByStoryId(storyId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
