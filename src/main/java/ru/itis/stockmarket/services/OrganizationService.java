package ru.itis.stockmarket.services;

import ru.itis.stockmarket.dtos.BankRequestDto;
import ru.itis.stockmarket.dtos.BankResponseDto;

import java.util.List;
import java.util.UUID;

public interface OrganizationService<RequestDto, ResponseDto> {
    ResponseDto createOrganization(RequestDto organizationDto);

    ResponseDto updateOrganization(UUID id, RequestDto partialOrganizationDto);

    ResponseDto getOrganizationWithId(UUID id);

    List<ResponseDto> getAllOrganizations();

    UUID deleteOrganizationWithId(UUID id);

}
