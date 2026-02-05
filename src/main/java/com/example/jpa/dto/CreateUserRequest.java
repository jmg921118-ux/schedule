package com.example.jpa.dto;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class CreateUserRequest {

    private String name;
    private String email;
    private String address;
}
