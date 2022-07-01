package ru.itis.stockmarket.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itis.stockmarket.dtos.*;
import ru.itis.stockmarket.services.OrganizationService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/organization" )
public class OrganizationController {

    private final OrganizationService<OrganizationRequestDto, OrganizationResponseDto> organizationService;

    public OrganizationController(OrganizationService<OrganizationRequestDto, OrganizationResponseDto> organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    ResponseEntity<InnerIdResponseDto> registerOrganization(@Valid @RequestBody OrganizationRequestDto dto) {
    OrganizationResponseDto serviceResponse = this.organizationService.createOrganization(dto);
    InnerIdResponseDto controllerResponse = InnerIdResponseDto
            .builder()
            .innerid(serviceResponse.getInnerId())
            .status(Status.success)
            .description("Organization created successfully")
            .build();
    return ResponseEntity.status(HttpStatus.CREATED).body(controllerResponse);
    }

    @PatchMapping("/{id}")
    GeneralMessage<OrganizationResponseDto> updateOrganization(
            @Validated(OnUpdate.class) @RequestBody OrganizationRequestDto dto, @PathVariable UUID id) {
        OrganizationResponseDto serviceResponse = this.organizationService.updateOrganization(id, dto);
        return new GeneralMessage<OrganizationResponseDto>().toBuilder().data(serviceResponse).build();
    }

    @GetMapping
    GeneralMessage<List<OrganizationResponseDto>> getAllOrganizations() {
        return new GeneralMessage<List<OrganizationResponseDto>>().toBuilder()
                .data(this.organizationService.getAllOrganizations()).build();
    }
    @GetMapping("/{id}")
    GeneralMessage<OrganizationResponseDto> getOrganizationWithId(@PathVariable UUID id) {
        return new GeneralMessage<OrganizationResponseDto>()
                .toBuilder()
                .data(this.organizationService.getOrganizationWithId(id))
                .build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<InnerIdResponseDto> deleteOrgWithId(@PathVariable UUID id) {
        return ResponseEntity.ok(
                InnerIdResponseDto.builder()
                        .status(Status.success)
                        .innerid(this.organizationService.deleteOrganizationWithId(id))
                        .description("Organization successfully deleted")
                        .build()
        );

    }
}
