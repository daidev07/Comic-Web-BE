package com.example.comicwebbe.controller;

import com.example.comicwebbe.dto.AddOneUserRequest;
import com.example.comicwebbe.dto.LoginUserRequest;
import com.example.comicwebbe.dto.UpdateStoryRequest;
import com.example.comicwebbe.dto.UpdateUserRequest;
import com.example.comicwebbe.entity.User;
import com.example.comicwebbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUser() {
        try {
            List<User> list = userService.getAllUser();
            if (!list.isEmpty()) {
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<User> registerUser(@RequestBody AddOneUserRequest addOneUserRequest) {
        try {
            User user = userService.addOneUser(addOneUserRequest);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<User>> loginUser(@RequestBody LoginUserRequest loginUserRequest){
        try {
            Optional<User> user = userService.loginUser(loginUserRequest);
            if (!user.isEmpty()) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @ModelAttribute UpdateUserRequest updateUserRequest){
        try{
            User updatedUser = userService.updateUser(userId, updateUserRequest);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
