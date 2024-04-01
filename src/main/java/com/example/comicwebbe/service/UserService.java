package com.example.comicwebbe.service;

import com.example.comicwebbe.dto.AddOneUserRequest;
import com.example.comicwebbe.dto.LoginUserRequest;
import com.example.comicwebbe.entity.User;
import com.example.comicwebbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public User addOneUser(AddOneUserRequest addOneUserRequest){
        User user = new User();
        user.setHoten(addOneUserRequest.getName());
        user.setEmail(addOneUserRequest.getEmail());
        user.setUsername(addOneUserRequest.getUsername());
        user.setPassword(addOneUserRequest.getPassword());
        user.setIs_admin(false);
        return userRepository.save(user);
    }
    public Optional<User> loginUser(LoginUserRequest loginUserRequest){
        return userRepository.findByUsernameAndPassword(loginUserRequest.getUsername(), loginUserRequest.getPassword());
    }
}
