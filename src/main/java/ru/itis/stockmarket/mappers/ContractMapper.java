package ru.itis.stockmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.stockmarket.dtos.ContractResponseDto;
import ru.itis.stockmarket.dtos.OrganizationResponseDto;
import ru.itis.stockmarket.models.Contract;
import ru.itis.stockmarket.models.Organization;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ContractMapper {
    @Mapping(target = "isPaid", expression = "java(contract.getPaymentDate() == null ? false : true)")
    @Mapping(source = "contractDate", target = "createdAt")
    @Mapping(source = "innerId", target = "contractId")
    @Mapping(source = "product.innerId", target = "productId")
    @Mapping(target = "buyer.products", ignore = true)
    @Mapping(target="buyer.countryCode", source="buyer.country.code")
    ContractResponseDto toDto(Contract contract);
}
