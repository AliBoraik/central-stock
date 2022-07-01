package ru.itis.stockmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.itis.stockmarket.dtos.ProductRequestDto;
import ru.itis.stockmarket.dtos.ProductResponseDto;
import ru.itis.stockmarket.models.Product;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface ProductMapper {
    @Mapping(target = "unit", ignore = true)
    @Mapping(target = "catalog", ignore = true)
    void fromDto(ProductRequestDto dto, @MappingTarget Product product);

    @Mapping(target = "unit", source="unit.code")
    @Mapping(target="code", source = "catalog.code")
    @Mapping(target="sellerId", source = "seller.innerId")
    ProductResponseDto toDto(Product product);
    List<ProductResponseDto> toDto(List<Product> products);
}
