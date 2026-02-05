package com.example.jpa.service;

import com.example.jpa.dto.*;
import com.example.jpa.entity.User;
import com.example.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //저장
    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getAddress()
        );
        User savedUser = userRepository.save(user); // User 정보 save 하기

        return new CreateUserResponse(
                savedUser.getId(),
                savedUser.getTitle(),
                savedUser.getContents(),
                savedUser.getWriter()
        );
    }

    //단건 조회
    @Transactional(readOnly = true)
    public GetOneUserResponse getOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );
        return new GetOneUserResponse(
                user.getId(),
                user.getTitle(),
                user.getContents(),
                user.getWriter()
        );
    }

    //다건 조회
    @Transactional(readOnly = true)
    public List<GetOneUserResponse> getAll() {
        List<User> users = userRepository.findAll();

        List<GetOneUserResponse> dtos = new ArrayList<>();
        for (User user : users) {
            GetOneUserResponse dto = new GetOneUserResponse(
                    user.getId(),
                    user.getTitle(),
                    user.getContents(),
                    user.getWriter()
            );
            dtos.add(dto);
        }
        return dtos;

    }

    @Transactional
    public UpdateUserResponse update(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );

        user.update(
                request.getName(),
                request.getEmail(),
                request.getAddress()
        );

        return new UpdateUserResponse(
                user.getId(),
                user.getTitle(),
                user.getContents(),
                user.getWriter()
        );

    }

    @Transactional
    public void delete(Long userId) {
        boolean existence = userRepository.existsById(userId);

        if (!existence) {
            throw new IllegalStateException("없는 유저입니다.");
        }
        //유저가 있는 경우
        userRepository.deleteAllById(userId);
    }


}

