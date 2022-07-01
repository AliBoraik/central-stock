package ru.itis.stockmarket.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.Optional;



@Data
@Builder
public class ProductFilterDto {
    private double count;
    private String code;
    private int size;
    private int page;
    private String country;
}
