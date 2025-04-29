package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody 결합된 애너테이션: RESTful 웹 서비스의 컨트롤러임을 명시
@RequestMapping("/student") // 해당 컨트롤러의 모든 요청 URL이 "/student"로 시작함을 정의
public class StudentController {
    // 비즈니스 로직을 처리하는 service 객체를 주입받아 사용
    private final StudentService studentService;

    // cf) 의존성 주입 방식 3가지 (필드 주입, *생성자 주입*, Setter 주입)
    public StudentController(StudentService studentService) { // 매개변수로 StudentService 객체를 주입
        this.studentService = studentService;
    }

    // 1) 전체 학생 목록 조회 (GET)

    // 2) 특정 ID로 학생 조회 (GET)

    // 3) 새로운 학생 등록 (POST)

    // 4) 특정 ID의 학생 정보 수정 (PUT)

    // 5) 특정 ID의 학생 정보 삭제 (DELETE)
}