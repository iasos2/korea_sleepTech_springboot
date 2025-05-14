package com.example.korea_sleepTech_springboot.dto.user.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String password;
    private String confirmPassword;
}
