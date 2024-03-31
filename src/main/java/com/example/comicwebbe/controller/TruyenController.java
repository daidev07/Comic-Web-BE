package com.example.comicwebbe.controller;
import com.example.comicwebbe.dto.SuaTruyen;
import com.example.comicwebbe.dto.ThemTruyen;
import com.example.comicwebbe.entity.Truyen;
import com.example.comicwebbe.service.TruyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/home")
public class TruyenController {
    @Autowired
    private TruyenService truyenService;

    @GetMapping("")
    public ResponseEntity<List<Truyen>> getAllTruyen(){
        try{
            List<Truyen> list = truyenService.getAllTruyen();
            if(!list.isEmpty()){
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStory(@RequestBody ThemTruyen themTruyen){
        try{
            truyenService.addStory(themTruyen);
            return ResponseEntity.ok("Thêm thành công");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateStory(@RequestBody SuaTruyen suaTruyen){
        try{
            truyenService.updateStory(suaTruyen);
            return ResponseEntity.ok("Sửa thành công");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteTran(@PathVariable Long id){
        try {
            truyenService.deleteById(id);
            return ResponseEntity.ok("Xóa thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
