package ru.itis.stockmarket.dtos;

import lombok.Data;
import org.hibernate.annotations.NotFound;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ContractRequestDto {
    @NotNull
    private UUID productid;
    @DecimalMin("0")
    private double count;
    @NotNull
    private UUID buyerid;

}
