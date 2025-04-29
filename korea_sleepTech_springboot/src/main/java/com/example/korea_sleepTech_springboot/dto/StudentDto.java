package com.example.korea_sleepTech_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDto {
    // 데이터 전송 시 email 필드값은 엔티티에서 제외함
    private Long id;
    private String name;
}
