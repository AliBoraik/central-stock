package ru.itis.stockmarket.mappers;

import org.mapstruct.*;
import ru.itis.stockmarket.dtos.BankRequestDto;
import ru.itis.stockmarket.models.Bank;


@Mapper(componentModel = "spring")
public interface BankMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "country.code", source = "country")
    void updateBankFromDto(BankRequestDto dto, @MappingTarget Bank entity);
}
