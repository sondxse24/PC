package com.pc.controller;

import com.pc.dto.ApiResponse;
import com.pc.dto.UserDTO;
import com.pc.model.Users;
import com.pc.repository.UserRepo;
import com.pc.service.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserSer userSer;

    @PostMapping
    private ResponseEntity<ApiResponse<?>> create(UserDTO user) {
        userSer.create(user);

        ApiResponse<Users> response = new ApiResponse<>(
                200,
                "Create account successful",
                null
        );
        return ResponseEntity.ok(response);
    }   
}
