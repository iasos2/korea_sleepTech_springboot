package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.request.BookCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.BookUpdateRequestDto;
import com.example.korea_sleepTech_springboot.dto.response.BookResponseDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.BOOK_API)
@RequiredArgsConstructor // final 필드를 매개변수로 가지는 생성자 생성 (+ 의존성 주입의 역할까지 자동 처리)
public class BookController {
    // Service 객체를 주입받아 저장하는 변수
    private final BookService bookService;

//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

    // 1. 기본 CRUD
    // 1) CREATE - BOOK 생성
    @PostMapping
    public ResponseEntity<ResponseDto<BookResponseDto>> createBook(@RequestBody BookCreateRequestDto dto) {
        try {
            ResponseDto<BookResponseDto> book = bookService.createBook(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // 2) READ - 전체 책 조회
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        List<BookResponseDto> books = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    // 3) READ - 단건 책 조회 (특정 ID)
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        BookResponseDto book = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    // 4) UPDATE - 책 수정 (특정 ID)
    @PutMapping
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @RequestBody BookUpdateRequestDto dto) {
        BookResponseDto book = bookService.updateBook(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    // 5) DELETE - 책 삭제 (특정 ID)
    @DeleteMapping
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    
    // 2. 검색 & 필터링 (@RequestParam)
    //: GET 메서드
    
    // 1) 제목에 특정 단어가 포함된 책 조회
    @GetMapping("/search/title")
    public ResponseEntity<ResponseDto<List<BookResponseDto>>> getBooksByTitleContaining(
            @RequestParam String keyword
            // 경로값에 ? 이후의 데이터를 키-값의 쌍으로 추출되는 값 (?키=값)
            // >> 키의 변수에 값이 할당
            // >> localhost:8080/books/search/title?keyword=xxxx
            
            // +) @RequestParam은 항상 문자열로 반환
            // : 숫자형은 int, long 으로 자동 변환
            // - 변환 실패 시 400 에러가 발생
    ) {
        try {
            ResponseDto<List<BookResponseDto>> books = bookService.getBooksByTitleContaining(keyword);
            return ResponseEntity.status(HttpStatus.OK).body(books);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    // 2) 카테고리별 책 조회
    
    // 3) 카테고리와 작성자별 책 조회
}