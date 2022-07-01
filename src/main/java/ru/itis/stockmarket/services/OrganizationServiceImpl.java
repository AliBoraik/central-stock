package ru.itis.stockmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.stockmarket.dtos.OrganizationRequestDto;
import ru.itis.stockmarket.dtos.OrganizationResponseDto;
import ru.itis.stockmarket.exceptions.NotFoundException;
import ru.itis.stockmarket.mappers.OrganizationMapper;
import ru.itis.stockmarket.models.Country;
import ru.itis.stockmarket.models.Organization;
import ru.itis.stockmarket.repositories.CountryRepository;
import ru.itis.stockmarket.repositories.OrganizationRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService<OrganizationRequestDto, OrganizationResponseDto> {
    private final OrganizationRepository organizationRepository;
    private final CountryRepository countryRepository;
    private final OrganizationMapper organizationMapper;

    @Override
    public OrganizationResponseDto createOrganization(OrganizationRequestDto organizationDto) {
        // get or set the country
        Country country = this.countryRepository
                .findCountryByCode(organizationDto.getCountry())
                .or(() -> {
                    Country c = new Country();
                    c.setCode(organizationDto.getCountry().toLowerCase()); // lombok cannot set from superClass
                    this.countryRepository.save(c);
                    return Optional.of(c);
                }).get();

        /* check if an organization with that name, address and country already exist */
        Optional<Organization> alreadyExists = this.organizationRepository.findByAddressAndNameAndCountry(
                organizationDto.getAddress(),
                organizationDto.getName(),
                country);
        if (alreadyExists.isPresent()) {
            return this.organizationMapper.toDto(alreadyExists.get());
        }

        // set the organization data
        Organization org = Organization.builder()
                .country(country)
                .build();
        this.organizationMapper.fromDto(organizationDto, org); // map the remaining dto data
        this.organizationRepository.save(org);
        return this.organizationMapper.toDto(org);
    }

    @Override
    public OrganizationResponseDto updateOrganization(UUID id, OrganizationRequestDto partialOrganizationDto) {
        Organization org = _getOrgWithId(id);
        this.organizationMapper.updateFromDto(partialOrganizationDto, org);
        this.organizationRepository.save(org);
        return this.organizationMapper.toDto(org);
    }

    public Organization _getOrgWithId(UUID id) {
        return this.organizationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Bank with id %s not found", id))
                );
    }

    @Override
    public OrganizationResponseDto getOrganizationWithId(UUID id) {
        return this.organizationMapper.toDto(_getOrgWithId(id));
    }

    @Override
    public List<OrganizationResponseDto> getAllOrganizations() {
        return this.organizationMapper.toDto(this.organizationRepository.findAll());
    }

    @Override
    public UUID deleteOrganizationWithId(UUID id) {
        Organization _org = _getOrgWithId(id);
        this.organizationRepository.delete(_org);
        return id;
    }
}
