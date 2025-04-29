package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.StudentDto;
import com.example.korea_sleepTech_springboot.entity.B_Student;
import com.example.korea_sleepTech_springboot.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service // 비즈니스 로직을 처리하는 역할
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDto> getAllStudents() {
        // 전체 학생 데이터 조회
        List<StudentDto> studentDtos = null;

        try {
            List<B_Student> students = studentRepository.findAll(); // email 까지 포함되어있음

            // email을 포함하지 않는 Dto 형식으로 변환
            studentDtos = students.stream() // stream API로 데이터 처리 (전체 리스트 순회 + 각 요소에 동일 기능 적용)
                    .map(student -> new StudentDto (
                            student.getId(),
                            student.getName()
                    ))
                    .collect(Collectors.toList());

            // 컬렉션 프레임 워크 + stream API
            // 1) 컬렉션데이터.stream(): 스트림으로 변환
            // 2) .map(), .filter() 등 중간 연산
            // 3) 리스트 형태로 다시 변환 - .collect(Collectors.toList());

            return studentDtos;

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, // HTTP 상태 코드
                    "Error occurred while fetching student", // 에러 메시지
                    e // 예외 원인
            );
        }
    }

    public StudentDto getStudentById(Long id) {
        StudentDto studentDto = null;

        try {
            B_Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new Error("Student not found with id: " + id));

            studentDto = new StudentDto(
                    student.getId(),
                    student.getName()
            );

            return studentDto;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, // HTTP 상태 코드
                    "Error occurred while fetching student", // 에러 메시지
                    e // 예외 원인
            );
        }
    }

    public StudentDto createStudent(B_Student student) {
        StudentDto studentDto = null;

        try {
            B_Student savedStudent = studentRepository.save(student);

            // 저장되고 난 후 B_Student 객체를 DTO로 변환하여 반환
            studentDto = new StudentDto(
                    savedStudent.getId(),
                    student.getName()
            );

            return studentDto;

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, // HTTP 상태 코드
                    "Error occurred while fetching student", // 에러 메시지
                    e // 예외 원인
            );
        }
    }

    public StudentDto updateStudent(Long id, StudentDto studentdto) {
        StudentDto responseDto = null;

        try {
            B_Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new Error("Student not found with id: " + id));

            student.setName(studentdto.getName());

            B_Student updatedStudent = studentRepository.save(student);

            responseDto = new StudentDto(
                    updatedStudent.getId(),
                    updatedStudent.getName()
            );

            return studentdto;

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, // HTTP 상태 코드
                    "Error occurred while fetching student", // 에러 메시지
                    e // 예외 원인
            );
        }
    }

    public void deleteStudent(Long id) {
        
    }
}