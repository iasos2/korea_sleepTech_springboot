package com.example.korea_sleepTech_springboot.시험.service;

import com.example.korea_sleepTech_springboot.시험.dto.response.ProductResponseDto;
import com.example.korea_sleepTech_springboot.시험.entity.Product;
import com.example.korea_sleepTech_springboot.시험.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<ProductResponseDto> getAllProducts() {

        List<ProductResponseDto> productResponseDtos = null;

        try {
            List<Product> products = productRepository.findAll();

            productResponseDtos = products.stream()
                    .map(product -> new ProductResponseDto(
                            product.getId(),
                            product.getName()
                    ))
                    .collect(Collectors.toList());

            return productResponseDtos;

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, // HTTP 상태 코드
                    "Error occurred while fetching student", // 에러 메시지
                    e // 예외 원인
            );
        }
    }

    public ProductResponseDto getProductById(Long id) {
        ProductResponseDto productResponseDto = null;
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new Error("Product not found with id: " + id));

            productResponseDto = new ProductResponseDto(
                    product.getId(),
                    product.getName()
            );

            return productResponseDto;

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, // HTTP 상태 코드
                    "Error occurred while fetching product", // 에러 메시지
                    e // 예외 원인
            );
        }
    }

    public ProductResponseDto updateProduct(Long id, Product productDto) {
        ProductResponseDto responseDto = null;

        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new Error("Product not found with id: " + id));

            product.setName((String) productDto.getName());

            Product updatedProduct = productRepository.save(product);

            responseDto = new ProductResponseDto(
                    updatedProduct.getId(),
                    updatedProduct.getName()
            );

            return responseDto;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, // HTTP 상태 코드
                    "Error occurred while fetching product", // 에러 메시지
                    e // 예외 원인
            );
        }

    }

    public void deleteProduct(Long id) {
        try{
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new Error("Product not found with id: " + id));

            productRepository.deleteById(id);
        } catch(Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, // HTTP 상태 코드
                    "Error occurred while fetching student", // 에러 메시지
                    e // 예외 원인
            );
        }
    }
}
