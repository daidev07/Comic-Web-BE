package com.example.comicwebbe.controller;

import com.example.comicwebbe.entity.Chuong;
import com.example.comicwebbe.entity.LichSu;
import com.example.comicwebbe.service.ChuongService;
import com.example.comicwebbe.service.LichSuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/history")
public class LichSuController {
    @Autowired
    private LichSuService lichSuService;

    @GetMapping("")
    public ResponseEntity<List<LichSu>> getAllLichSu(){
        try{
            List<LichSu> list = lichSuService.getAllLichSu();
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
