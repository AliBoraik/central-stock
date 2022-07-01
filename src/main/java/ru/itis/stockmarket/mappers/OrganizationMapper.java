package ru.itis.stockmarket.mappers;

import org.mapstruct.*;
import ru.itis.stockmarket.dtos.OrganizationRequestDto;
import ru.itis.stockmarket.dtos.OrganizationResponseDto;
import ru.itis.stockmarket.models.Organization;
import ru.itis.stockmarket.models.Product;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    @Mapping(source = "country.code", target="countryCode")
    OrganizationResponseDto toDto(Organization org);

    default Collection<UUID> mapProductsToString(Collection<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream()
                .map(Product::getInnerId)
                .collect(Collectors.toList());
    }

    List<OrganizationResponseDto> toDto(List<Organization> organizations);

    @Mapping(target = "country.code", source = "country")
    void fromDto(OrganizationRequestDto dto, @MappingTarget Organization org);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "country.code", source = "country")
    void updateFromDto(OrganizationRequestDto dto, @MappingTarget Organization org);
}
