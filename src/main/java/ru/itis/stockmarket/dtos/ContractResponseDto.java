package ru.itis.stockmarket.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.UUID;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractResponseDto {
    @JsonProperty("contractid")
    private UUID contractId;
    @JsonProperty("productid")
    private UUID productId;
    private double count;
    private OrganizationResponseDto buyer;
    private Date createdAt;
    private Boolean isPaid;
    private Date paymentDate;
    private Date deliveryDate;
}
