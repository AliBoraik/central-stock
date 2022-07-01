package ru.itis.stockmarket.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class InnerIdResponseDto extends GeneralMessage<UUID> {
    private UUID innerid;
}
