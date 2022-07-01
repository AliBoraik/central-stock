package ru.itis.stockmarket.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralMessage<T> {
    @Builder.Default
    private Status status = Status.success;
    @Builder.Default
    private String description = "OK";
    @Nullable private T data;
}
