package com.example.korea_sleepTech_springboot.시험.service;

import com.example.korea_sleepTech_springboot.시험.dto.request.ProductCreateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.request.ProductUpdateRequestDto;
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

    // CREATE(생성)
    public ProductResponseDto createProduct(ProductCreateRequestDto productDto) {
        ProductResponseDto productResponseDto = null;

        try {
            Product product = new Product(
                    productDto.getName(),
                    productDto.getDescription()
            );

            Product savedProduct = productRepository.save(product);

            productResponseDto = new ProductResponseDto(
                    savedProduct.getId(),
                    product.getName()
            );

            return productResponseDto;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while fetching product", e);
        }
    }

    // READ(전체조회)
    public List<ProductResponseDto> getAllProduct() {
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
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while fetching product", e);
        }
    }

    // READ(단일조회)
    public ProductResponseDto getProductById(Long id) {
        ProductResponseDto productResponseDto = null;

        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new Error("Product not found with id: " + id));

            productResponseDto = new ProductResponseDto (
                    product.getId(),
                    product.getName()
            );

            return productResponseDto;
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while fetching product", e);
        }
    }

    // UPDATE(수정)
    public ProductUpdateRequestDto updateProduct(Long id, ProductUpdateRequestDto productDto) {
        ProductResponseDto responseDto = null;

        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new Error("Product not found with id: " + id));

            product.setName(productDto.getName());

            Product updatedProduct = productRepository.save(product);

            responseDto = new ProductResponseDto(
                    updatedProduct.getId(),
                    updatedProduct.getName()
            );

            return productDto;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while fetching product", e);
        }
    }

    // DELETE(삭제)
    public void deleteProduct(Long id) {
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new Error("Product not found with id: " + id));

            productRepository.delete(product);
        } catch (Exception e)  {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while fetching product", e);
        }
    }
}
