package ru.itis.stockmarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.stockmarket.models.Bank;
import ru.itis.stockmarket.models.Country;
import ru.itis.stockmarket.models.Product;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private String code;
    private String name;
    @JsonProperty(value = "productid")
    private UUID innerId;
    private double count;
    private Long unit;
    private double price;
    @JsonProperty(value="sellerid")
    private UUID sellerId;
}
