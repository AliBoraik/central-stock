package ru.itis.stockmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.stockmarket.models.Bank;
import ru.itis.stockmarket.models.Country;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * Date: 27.04.2022
 * Time: 11:58 AM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
public interface BankRepository extends JpaRepository<Bank, UUID> {
    boolean existsBankByCountry(Country country);
    Optional<Bank> findBankByCountry_Code(String code);
}
