package com.example.korea_sleepTech_springboot.dto.user.response;

import com.example.korea_sleepTech_springboot.entity.User;
import lombok.Getter;

@Getter
public class GetUserResponseDto {
    private Long id;
    private String email;

    public GetUserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }
}