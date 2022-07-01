package ru.itis.stockmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.stockmarket.models.Country;

import java.util.Optional;


public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findCountryByCode(String code);
}
