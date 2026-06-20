package com.groceryai.controller;

import com.groceryai.dto.LoginRequest;
import com.groceryai.dto.LoginResponse;
import com.groceryai.dto.RegisterRequest;
import com.groceryai.dto.RegisterResponse;
import com.groceryai.entity.User;
import com.groceryai.security.JwtService;
import com.groceryai.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public RegisterResponse register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {

        User user = userService.authenticate(
                request.getEmail(),
                request.getPassword()
        );

        String token = jwtService.generateToken(
                user.getEmail()
        );

        return new LoginResponse(token);
    }
}