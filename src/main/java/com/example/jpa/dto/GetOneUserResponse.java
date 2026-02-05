package com.example.jpa.dto;

import lombok.Getter;

@Getter
public class GetOneUserResponse {
    private final Long id;
    private final String name;
    private final String email;
    private  final String address;


    public GetOneUserResponse(Long id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
