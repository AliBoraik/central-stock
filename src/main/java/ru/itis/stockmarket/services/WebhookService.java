package ru.itis.stockmarket.services;

import ru.itis.stockmarket.dtos.ContractResponseDto;
import ru.itis.stockmarket.dtos.GeneralMessage;

import java.util.UUID;


public interface WebhookService {
    GeneralMessage<?> onCreateContract(ContractResponseDto contractDto, String sellerCountryCode);
    GeneralMessage<?> onPaymentMadeForContract(UUID contractId, String sellerCountryCode);
}
