package com.example.comicwebbe.dto;

import lombok.Data;

@Data
public class AddOneUserRequest {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
}
