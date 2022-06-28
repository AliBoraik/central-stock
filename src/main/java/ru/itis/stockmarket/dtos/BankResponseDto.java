package ru.itis.stockmarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.itis.stockmarket.models.Account;
import ru.itis.stockmarket.models.Bank;
import ru.itis.stockmarket.models.Country;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * Date: 29.04.2022
 * Time: 10:01 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankResponseDto {
    private String name;
    @JsonProperty(value = "innerid")
    private UUID innerId;
    private String address;
    @JsonProperty(value = "country_code")
    private String countryCode;
    private Map<String,Double> accounts;

    public static BankResponseDto from(Bank bank) {
        return BankResponseDto.builder()
                .name(bank.getName())
                .innerId(bank.getInnerId())
                .address(bank.getAddress())
                .countryCode(
                        Optional.ofNullable(bank.getCountry())
                                .map(Country::getCode)
                                .orElse(null))
                .accounts(bank.getAccounts()
                        .stream()
                        .collect(Collectors.toMap(acc -> acc.getCountry().getCode(),Account::getBalance)))
                .build();
    }

    public static List<BankResponseDto> from(List<Bank> banks) {
        return banks
                .stream()
                .map(BankResponseDto::from)
                .collect(Collectors.toList());
    }
}
