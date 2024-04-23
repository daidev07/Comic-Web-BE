package com.example.comicwebbe.controller;

import com.example.comicwebbe.dto.UserFavoriteRequest;
import com.example.comicwebbe.entity.Favorite;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/{userId}/{storyId}")
    public ResponseEntity<Optional<Favorite>> getOneByUserIdAndStoryId(@PathVariable Long userId, @PathVariable Long storyId){
        try{
            Optional<Favorite> favorite = favoriteService.getOneByUserIdAndStoryId(userId, storyId);
            if(!favorite.isEmpty()){
                return ResponseEntity.ok(favorite);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getListFavoriteStoryByUserId(@PathVariable Long userId){
        try{
            List<Story> list = favoriteService.getListFavoriteStoryByUserId(userId);
            if(!list.isEmpty()){
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bạn chưa yêu thích bộ truyện nào!");
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<String> addFavorite(@RequestBody UserFavoriteRequest userFavoriteRequest){
        try{
            favoriteService.addOne(userFavoriteRequest);
            return ResponseEntity.ok("Thêm thành công");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{userId}/{storyId}")
    public ResponseEntity<String> deleteFavoriteByUserIdAndStoryId(@PathVariable Long userId, @PathVariable Long storyId) {
        try {
            favoriteService.deleteFavoriteByUserIdAndStoryId(userId, storyId);
            return ResponseEntity.ok("Xóa thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
