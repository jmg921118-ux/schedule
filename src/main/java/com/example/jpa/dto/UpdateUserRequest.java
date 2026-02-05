package com.example.jpa.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequest {

    private String name;
    private String email;
    private String address;
}
