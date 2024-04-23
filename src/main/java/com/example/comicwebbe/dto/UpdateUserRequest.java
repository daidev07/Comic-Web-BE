package com.example.comicwebbe.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateUserRequest {
    private String hoten;
    private MultipartFile avtFile;
    private String password;
}
