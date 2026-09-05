package org.zapovednik.groupservice.service.impl;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zapovednik.groupservice.dto.request.GroupCountryRequestDto;
import org.zapovednik.groupservice.dto.response.GroupCountryResponseDto;
import org.zapovednik.groupservice.exception.custom.NotFoundException;
import org.zapovednik.groupservice.mapper.GroupCountryMapper;
import org.zapovednik.groupservice.model.entity.Country;
import org.zapovednik.groupservice.model.entity.GroupCountry;
import org.zapovednik.groupservice.model.entity.TourGroup;
import org.zapovednik.groupservice.model.entity.type.TourGroupStatus;
import org.zapovednik.groupservice.model.repository.CountryRepository;
import org.zapovednik.groupservice.model.repository.GroupCountryRepository;
import org.zapovednik.groupservice.model.repository.OrganizationRepository;
import org.zapovednik.groupservice.model.repository.RouteRepository;
import org.zapovednik.groupservice.model.repository.TourGroupRepository;
import org.zapovednik.groupservice.model.repository.projection.CountryStatisticsProjection;
import org.zapovednik.groupservice.service.GroupCountryService;

@Service
@RequiredArgsConstructor
public class GroupCountryServiceImpl implements GroupCountryService {
    private final GroupCountryRepository groupCountryRepository;
    private final CountryRepository countryRepository;
    private final TourGroupRepository tourGroupRepository;
    private final OrganizationRepository organizationRepository;
    private final RouteRepository routeRepository;
    private final GroupCountryMapper groupCountryMapper;

    @Override
    @Transactional
    public Long save(final Long tourGroupId, final GroupCountryRequestDto requestDto) {
        final TourGroup tourGroup = tourGroupRepository.findById(tourGroupId)
                .orElseThrow(() -> new NotFoundException("Tour group not found: " + tourGroupId));
        final Long countryId = requestDto.getCountryId();
        final Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new NotFoundException("Country not found: " + countryId));
        final GroupCountry groupCountry = groupCountryMapper.toEntity(requestDto);

        groupCountry.setTourGroup(tourGroup);
        groupCountry.setCountry(country);

        final GroupCountry savedGroupCountry = groupCountryRepository.save(groupCountry);

        return savedGroupCountry.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Long sumParticipantQuantityByStatusAndStartDateBetween(
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return groupCountryRepository.sumParticipantQuantityByStatusAndStartDateBetween(status, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Long sumParticipantQuantityByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return groupCountryRepository
                .sumParticipantQuantityByIsLegalAndStatusAndStartDateBetween(isLegal, status, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CountryStatisticsProjection> findCountryStatisticsByStatusCompletedAndStartDateBetween(
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return groupCountryRepository.findCountryStatisticsByStatusCompletedAndStartDateBetween(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Long sumParticipantQuantityByOrganizationIdAndStatusAndStartDateBetween(
            final Long organizationId,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (organizationRepository.existsById(organizationId)) {
            return groupCountryRepository
                    .sumParticipantQuantityByOrganizationIdAndStatusAndStartDateBetween(
                            organizationId, status, startDate, endDate);
        } else {
            throw new NotFoundException("Organization not found: " + organizationId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long sumParticipantQuantityByRouteIdAndStatusCompletedAndStartDateBetween(
            final Long routeId,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (routeRepository.existsById(routeId)) {
            return groupCountryRepository
                    .sumParticipantQuantityByRouteIdAndStatusCompletedAndStartDateBetween(
                            routeId, startDate, endDate);
        } else {
            throw new NotFoundException("Route not found: " + routeId);
        }
    }

    @Override
    @Transactional
    public GroupCountryResponseDto update(final Long groupCountryId, final GroupCountryRequestDto requestDto) {
        final GroupCountry groupCountry = groupCountryRepository.findById(groupCountryId)
                .orElseThrow(() -> new NotFoundException("Group country not found: " + groupCountryId));
        final Long countryId = requestDto.getCountryId();
        final Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new NotFoundException("Country not found: " + countryId));

        groupCountry.setCountry(country);
        groupCountry.setParticipantQuantity(requestDto.getParticipantQuantity());

        return groupCountryMapper.toDto(groupCountry);
    }
}