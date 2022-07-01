package ru.itis.stockmarket.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.itis.stockmarket.dtos.ContractResponseDto;
import ru.itis.stockmarket.dtos.GeneralMessage;
import ru.itis.stockmarket.dtos.PaymentRequestDto;
import ru.itis.stockmarket.dtos.Status;

import java.util.UUID;


@Service
@Slf4j
public class WebhookServiceImpl implements WebhookService {

    private final RestTemplate restTemplate;

    private final String BASE_URL = "http://188.93.211.195";

    WebhookServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public GeneralMessage<?> onCreateContract(ContractResponseDto contractDto, String sellerCountryCode) {
        String url = BASE_URL + "/" + sellerCountryCode + "/contract";
        try {
            log.info(String.format("onCreateContract for contract %s", contractDto.getContractId()));
            HttpEntity<ContractResponseDto> request = new HttpEntity<>(contractDto);
            GeneralMessage<?> response = restTemplate.postForObject(url, request, GeneralMessage.class);
            logResponse("onCreateContract to " + url , response);
            return response;
        } catch (RestClientException ex) {
            ex.printStackTrace();
            log.error(String.format("Caught error from (%s): %s", url, ex.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public GeneralMessage<?> onPaymentMadeForContract(UUID contractId, String sellerCountryCode) {
        try {
            log.info(String.format("onPaymentMadeForContract for contract %s", contractId));
            HttpEntity<PaymentRequestDto> request = new HttpEntity<>(
                    PaymentRequestDto.builder()
                            .contractId(contractId)
                            .build());
            String url = BASE_URL + "/" + sellerCountryCode + "/payment";
            GeneralMessage<?> response = restTemplate.postForObject(url, request, GeneralMessage.class);
            logResponse("onPaymentMadeForContract", response);
            return response;
        } catch (RestClientException ex) {
            ex.printStackTrace();
            log.error("An error was caught onPaymentMadeForContract with: " + ex.getLocalizedMessage());
            return null;
        }
    }

    private void logResponse(String methodName, GeneralMessage<?> response) {
        if (response == null) {
            log.error(methodName + ": Null Response from request");
        } else if (response.getStatus() == Status.failure) {
            log.error(methodName + ": Returned with status 'failure' and description " + response.getDescription());
        } else {
            log.info(methodName + ": Returned with status 'success' and description " + response.getDescription());
        }
    }
}
