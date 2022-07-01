package ru.itis.stockmarket.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.itis.stockmarket.dtos.PagedResponse;
import ru.itis.stockmarket.dtos.ProductFilterDto;
import ru.itis.stockmarket.dtos.ProductRequestDto;
import ru.itis.stockmarket.dtos.ProductResponseDto;
import ru.itis.stockmarket.models.Product;

import java.util.List;
import java.util.UUID;


public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productDto);
    ProductResponseDto getProduct(UUID id);
    PagedResponse<ProductResponseDto> getProducts(ProductFilterDto dto);
    PagedResponse<ProductResponseDto> getAllProducts(Pageable pageable);
}
