package ru.itis.stockmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.stockmarket.models.Product;

import java.util.UUID;


public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {

    boolean existsProductBySeller_InnerIdAndName(UUID seller_id, String name);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Product p SET p.count = p.count - :count, p.frozenCount = p.frozenCount + :count WHERE p.innerId = :id")
    void freezeCountById(@Param("count") double count, @Param("id") UUID id);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Product p SET p.count = p.count + :count, p.frozenCount = p.frozenCount - :count WHERE p.innerId = :id")
    void unfreezeCountById(@Param("count") double count, @Param("id") UUID id);
}
