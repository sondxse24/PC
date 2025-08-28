package com.pc.service;

import com.pc.dto.UserDTO;
import com.pc.model.Users;
import com.pc.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserSer {

    @Autowired
    private UserRepo userRepo;

    public void create(UserDTO dto){
        Users user = new Users();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setCreateAt(LocalDateTime.now());
        userRepo.save(user);
    }
}
