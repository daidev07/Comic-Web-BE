package com.example.comicwebbe.controller;

import com.example.comicwebbe.entity.Favorite;
import com.example.comicwebbe.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("")
    public ResponseEntity<List<Favorite>> getAllYeuThich(){
        try{
            List<Favorite> list = favoriteService.getAllYeuThich();
            if(!list.isEmpty()){
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
