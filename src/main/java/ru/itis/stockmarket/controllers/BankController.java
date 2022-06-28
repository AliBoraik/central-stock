package ru.itis.stockmarket.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itis.stockmarket.dtos.*;
import ru.itis.stockmarket.services.BankService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * Date: 27.04.2022
 * Time: 10:51 AM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */


@RestController
@RequestMapping(path = "/bank")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping
    ResponseEntity<InnerIdResponseDto> createBank(@Valid @RequestBody BankRequestDto bank) {
        BankResponseDto bankResponse = this.bankService.createOrganization(bank);
        InnerIdResponseDto response = InnerIdResponseDto.builder()
                .status(Status.success)
                .description("Bank created successfully")
                .innerid(bankResponse.getInnerId())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    GeneralMessage<List<BankResponseDto>> getAllBanks() {
        return new GeneralMessage<List<BankResponseDto>>()
                .toBuilder()
                .data(this.bankService.getAllOrganizations())
                .build();
    }

    @GetMapping("/{id}")
    GeneralMessage<BankResponseDto> getBankWithId(@PathVariable UUID id) {
        return new GeneralMessage<BankResponseDto>()
                .toBuilder()
                .data(this.bankService.getOrganizationWithId(id))
                .build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<InnerIdResponseDto> deleteBankWithId(@PathVariable UUID id) {
        UUID responseId = this.bankService.deleteOrganizationWithId(id);
        return ResponseEntity.ok(InnerIdResponseDto.builder()
                .description("Successfully deleted bank")
                .innerid(responseId)
                .build());
    }

    @PatchMapping("/{id}")
    GeneralMessage<BankResponseDto> updateBankWithId(@PathVariable("id") UUID id,
                                                     @Validated(OnUpdate.class) @RequestBody BankRequestDto bank) {
        return new GeneralMessage<BankResponseDto>()
                .toBuilder()
                .data(this.bankService.updateOrganization(id, bank))
                .build();
    }
}
