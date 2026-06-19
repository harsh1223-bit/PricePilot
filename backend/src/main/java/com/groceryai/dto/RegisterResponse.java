package com.groceryai.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RegisterResponse {

    private Long id;
    private String fullName;
    private String email;
    private LocalDateTime createdAt;
}