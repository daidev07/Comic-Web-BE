package com.example.comicwebbe.service;

import com.example.comicwebbe.dto.AddOneUserRequest;
import com.example.comicwebbe.dto.LoginUserRequest;
import com.example.comicwebbe.dto.UpdateStoryRequest;
import com.example.comicwebbe.dto.UpdateUserRequest;
import com.example.comicwebbe.entity.Category;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.entity.StoryCategory;
import com.example.comicwebbe.entity.User;
import com.example.comicwebbe.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        user.setAvt("avt.jpg");
        return userRepository.save(user);
    }
    public Optional<User> loginUser(LoginUserRequest loginUserRequest){
        return userRepository.findByUsernameAndPassword(loginUserRequest.getUsername(), loginUserRequest.getPassword());
    }

    @Transactional
    public User updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        try {
            // Kiểm tra story có tồn tại không
            Optional<User> existingUserOptional = userRepository.findById(userId);
            if (existingUserOptional.isPresent()) {
                User existingUser = existingUserOptional.get();

                if(updateUserRequest.getHoten() != null && !updateUserRequest.getHoten().isEmpty()){
                    existingUser.setHoten(updateUserRequest.getHoten());
                }

                if(updateUserRequest.getPassword() != null && !updateUserRequest.getPassword().isEmpty()){
                    existingUser.setPassword(updateUserRequest.getPassword());
                }

                if(updateUserRequest.getAvtFile() != null){
                    String avtImageUrl = saveImage(updateUserRequest.getAvtFile());
                    existingUser.setAvt(avtImageUrl);
                }

                return userRepository.save(existingUser);

            } else {
                throw new RuntimeException("Không tìm thấy tài khoản có ID: " + userId);
            }
        } catch (Exception e) {
            System.out.println("error::" + e);
            throw new RuntimeException(e);
        }
    }

    public String saveImage(MultipartFile image) {
        try {
            String uploadDir = "uploads/";

            String uniqueFileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Lưu ảnh vào thư mục
            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Trả về URL của ảnh đã lưu
            return uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu trữ tệp ảnh: " + e.getMessage());
        }
    }
}
