package com.example.comicwebbe.service;

import com.example.comicwebbe.entity.User;
import com.example.comicwebbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
    public void findById(Long id) {
        userRepository.findById(id);
    }
}
