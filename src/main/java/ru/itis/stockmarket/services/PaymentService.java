package ru.itis.stockmarket.services;

import ru.itis.stockmarket.dtos.InnerIdResponseDto;

import java.util.UUID;

public interface PaymentService {
    InnerIdResponseDto makePaymentForContract(UUID contractId);
}
