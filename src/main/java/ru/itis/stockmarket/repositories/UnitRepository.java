package ru.itis.stockmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.stockmarket.models.ProductCatalog;
import ru.itis.stockmarket.models.Unit;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    Optional<Unit> findUnitByCode(Long code);
}
