package ru.itis.stockmarket.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.stockmarket.dtos.*;
import ru.itis.stockmarket.services.ProductService;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.UUID;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/product")
    ResponseEntity<InnerIdResponseDto> createProduct(@Valid @RequestBody ProductRequestDto product) {
        ProductResponseDto serviceProduct = productService.createProduct(product);

        InnerIdResponseDto controllerResponse = InnerIdResponseDto
                .builder()
                .status(Status.success)
                .description("Product created successfully")
                .innerid(serviceProduct.getInnerId())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(controllerResponse);
    }

    @GetMapping("/product/{id}")
    ResponseEntity<ProductResponseDto> getSingleProduct(@PathVariable UUID id) {
        return ResponseEntity.ok(this.productService.getProduct(id));
    }

    @GetMapping("/productlist")
    GeneralMessage<PagedResponse<ProductResponseDto>> getProductList(
            @RequestParam String code,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "0") double count,
            @RequestParam(required = false) String country
    ) {
        ProductFilterDto dto = ProductFilterDto.builder()
                .size(size).page(page).count(count).country(country).code(code)
                .build();
        return new GeneralMessage<PagedResponse<ProductResponseDto>>()
                .toBuilder()
                .data(this.productService.getProducts(dto))
                .build();
    }
}
