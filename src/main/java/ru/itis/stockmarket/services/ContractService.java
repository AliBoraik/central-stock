package ru.itis.stockmarket.services;

import org.springframework.data.domain.Pageable;
import ru.itis.stockmarket.dtos.ContractRequestDto;
import ru.itis.stockmarket.dtos.ContractResponseDto;
import ru.itis.stockmarket.dtos.PagedResponse;

import java.util.UUID;

public interface ContractService {
    ContractResponseDto findContractById(UUID id);
    PagedResponse<ContractResponseDto> getAllContracts(Pageable pageable);
    ContractResponseDto createContract(ContractRequestDto dto);
    void deleteContractWithId(UUID id);
}
