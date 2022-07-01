package ru.itis.stockmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.stockmarket.models.Country;
import ru.itis.stockmarket.models.Organization;

import java.util.Optional;
import java.util.UUID;


public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    Optional<Organization> findByAddressAndNameAndCountry(String address, String name, Country country);
}
