package com.example.jpa.controller;


import com.example.jpa.dto.*;
import com.example.jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 유저 생성 정보를 가져옴
    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
       CreateUserResponse result = userService.save(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<GetOneUserResponse> getOneUser(@PathVariable Long userId) {
      GetOneUserResponse result = userService.getOne(userId);
      return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users")
    public ResponseEntity<List<GetOneUserResponse>> getAllUsers() {
       List<GetOneUserResponse> result = userService.getAll();
       return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PutMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponse> update(
            @PathVariable Long userId,
            @RequestBody UpdateUserRequest request) {
       UpdateUserResponse result = userService.update(userId, request);
       return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
