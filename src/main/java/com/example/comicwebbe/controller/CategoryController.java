package com.example.comicwebbe.controller;

import com.example.comicwebbe.dto.AddCategoryRequest;
import com.example.comicwebbe.dto.AddStoryRequest;
import com.example.comicwebbe.entity.Category;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;



    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getDetailCate(@PathVariable Long id) {
        try {
            Optional<Category> category = categoryService.findById(id);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllTheLoai() {
        try {
            List<Category> list = categoryService.getAllTheLoai();
            if (!list.isEmpty()) {
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
        try {
            categoryService.addCate(addCategoryRequest);
            return ResponseEntity.ok("Thêm thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteCate(@PathVariable Long id) {
        try {
            categoryService.deleteById(id);
            return ResponseEntity.ok("Xóa thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi không xác định");
        }
    }
}