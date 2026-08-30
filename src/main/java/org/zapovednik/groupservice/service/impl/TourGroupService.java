package org.zapovednik.groupservice.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zapovednik.groupservice.dto.request.TourGroupExpectationRequestDto;
import org.zapovednik.groupservice.dto.request.TourGroupProcessingRequestDto;
import org.zapovednik.groupservice.dto.response.TourGroupExpectationResponseDto;
import org.zapovednik.groupservice.dto.response.TourGroupResponseDto;
import org.zapovednik.groupservice.exception.custom.NotFoundException;
import org.zapovednik.groupservice.mapper.TourGroupMapper;
import org.zapovednik.groupservice.model.entity.TourGroup;
import org.zapovednik.groupservice.model.entity.type.TourGroupStatus;
import org.zapovednik.groupservice.model.repository.DriverRepository;
import org.zapovednik.groupservice.model.repository.OrganizationRepository;
import org.zapovednik.groupservice.model.repository.ResponsibleSpecialistRepository;
import org.zapovednik.groupservice.model.repository.RouteRepository;
import org.zapovednik.groupservice.model.repository.TourGroupRepository;

@Service
@RequiredArgsConstructor
public class TourGroupService {
    private final TourGroupRepository tourGroupRepository;
    private final RouteRepository routeRepository;
    private final OrganizationRepository organizationRepository;
    private final TourGroupMapper tourGroupMapper;
    private final DriverRepository driverRepository;
    private final ResponsibleSpecialistRepository responsibleSpecialistRepository;

    // TODO Figure out what DTO needs to be returned. Probably just need to return only id.

    @Transactional
    public TourGroupExpectationResponseDto save(final TourGroupExpectationRequestDto requestDto) {
        final TourGroup tourGroup = tourGroupMapper.toCreateEntity(requestDto);
        final Long routeId = requestDto.getRouteId();

        tourGroup.setStatus(TourGroupStatus.EXPECTATION);
        tourGroup.setRoute(routeRepository.getReferenceById(routeId));

        if (requestDto.getIsLegal()) {
            final Long organizationId = requestDto.getOrganizationId();

            tourGroup.setOrganization(organizationRepository.getReferenceById(organizationId));
        }

        final TourGroup savedTourGroup = tourGroupRepository.save(tourGroup);

        return tourGroupMapper.toCreateDto(savedTourGroup);
    }

    @Transactional(readOnly = true)
    public TourGroupResponseDto findById(final Long tourGroupId) {
        final TourGroup tourGroup = tourGroupRepository.findById(tourGroupId)
                .orElseThrow(() -> new NotFoundException("Tour group not found: " + tourGroupId));

        return tourGroupMapper.toDto(tourGroup);
    }

    @Transactional(readOnly = true)
    public List<TourGroupResponseDto> findAllByStatusIn(final List<TourGroupStatus> tourGroupStatuses) {
        final List<TourGroup> tourGroupList = tourGroupRepository.findAllByStatusIn(tourGroupStatuses);

        return tourGroupList.stream()
                .map(tourGroupMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TourGroupResponseDto> findAllByStartDateBetweenAndStatusIn(
            final LocalDate startDate,
            final LocalDate endDate,
            final List<TourGroupStatus> tourGroupStatuses
    ) {
        final List<TourGroup> tourGroupList = tourGroupRepository
                .findAllByStartDateBetweenAndStatusIn(startDate, endDate, tourGroupStatuses);

        return tourGroupList.stream()
                .map(tourGroupMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TourGroupResponseDto> findAllByStartDateBetweenAndOrganizationIdAndStatusIn(
            final LocalDate startDate,
            final LocalDate endDate,
            final Long organizationId,
            final List<TourGroupStatus> tourGroupStatuses
    ) {
        if (!organizationRepository.existsById(organizationId)) {
            throw new NotFoundException("Organization not found: " + organizationId);
        }

        final List<TourGroup> tourGroupList = tourGroupRepository
                .findAllByStartDateBetweenAndOrganizationIdAndStatusIn(startDate, endDate, organizationId,
                        tourGroupStatuses);

        return tourGroupList.stream()
                .map(tourGroupMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TourGroupResponseDto> findAllByStartDateBetweenAndStatusCompletedAndIsPaidTrue(
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        final List<TourGroup> tourGroupList = tourGroupRepository
                .findAllByStartDateBetweenAndStatusCompletedAndIsPaidTrue(startDate, endDate);

        return tourGroupList.stream()
                .map(tourGroupMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TourGroupResponseDto> findAllByStartDateBetweenAndStatusCompletedAndIsPaidFalse(
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        final List<TourGroup> tourGroupList = tourGroupRepository
                .findAllByStartDateBetweenAndStatusCompletedAndIsPaidFalse(startDate, endDate);

        return tourGroupList.stream()
                .map(tourGroupMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TourGroupResponseDto> findAllByStartDateBetweenAndStatusCompletedAndIsDocumentsSubmittedTrue(
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        final List<TourGroup> tourGroupList = tourGroupRepository
                .findAllByStartDateBetweenAndStatusCompletedAndIsDocumentsSubmittedTrue(startDate, endDate);

        return tourGroupList.stream()
                .map(tourGroupMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TourGroupResponseDto> findAllByStartDateBetweenAndStatusCompletedAndIsDocumentsSubmittedFalse(
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        final List<TourGroup> tourGroupList = tourGroupRepository
                .findAllByStartDateBetweenAndStatusCompletedAndIsDocumentsSubmittedFalse(startDate, endDate);

        return tourGroupList.stream()
                .map(tourGroupMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Long countByStatusAndStartDateBetween(
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return tourGroupRepository.countByStatusAndStartDateBetween(status, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public Long countByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return tourGroupRepository.countByIsLegalAndStatusAndStartDateBetween(isLegal, status, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public BigDecimal sumPriceByStatusAndStartDateBetween(
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return tourGroupRepository.sumPriceByStatusAndStartDateBetween(status, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public BigDecimal sumPriceByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return tourGroupRepository.sumPriceByIsLegalAndStatusAndStartDateBetween(isLegal, status, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public Long countByOrganizationIdAndStatusAndStartDateBetween(
            final Long organizationId,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (!organizationRepository.existsById(organizationId)) {
            throw new NotFoundException("Organization not found: " + organizationId);
        }

        return tourGroupRepository
                .countByOrganizationIdAndStatusAndStartDateBetween(organizationId, status, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public BigDecimal sumPriceByOrganizationIdAndStatusAndStartDateBetween(
            final Long organizationId,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (!organizationRepository.existsById(organizationId)) {
            throw new NotFoundException("Organization not found: " + organizationId);
        }

        return tourGroupRepository
                .sumPriceByOrganizationIdAndStatusAndStartDateBetween(organizationId, status, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public Long countByDriverIdAndStatusCompletedAndStartDateBetween(
            final Long driverId,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (!driverRepository.existsById(driverId)) {
            throw new NotFoundException("Driver not found: " + driverId);
        }

        return tourGroupRepository
                .countByDriverIdAndStatusCompletedAndStartDateBetween(driverId, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public Long countByResponsibleSpecialistIdAndStatusCompletedAndStartDateBetween(
            final Long responsibleSpecialistId,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (!responsibleSpecialistRepository.existsById(responsibleSpecialistId)) {
            throw new NotFoundException("Responsible specialist not found: " + responsibleSpecialistId);
        }

        return tourGroupRepository
                .countByResponsibleSpecialistIdAndStatusCompletedAndStartDateBetween(responsibleSpecialistId, startDate,
                        endDate);
    }

    @Transactional(readOnly = true)
    public Long countByRouteIdAndStatusCompletedAndStartDateBetween(
            final Long routeId,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (!routeRepository.existsById(routeId)) {
            throw new NotFoundException("Route not found: " + routeId);
        }

        return tourGroupRepository
                .countByRouteIdAndStatusCompletedAndStartDateBetween(routeId, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public BigDecimal sumPriceByRouteIdAndStatusCompletedAndStartDateBetween(
            final Long routeId,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (!routeRepository.existsById(routeId)) {
            throw new NotFoundException("Route not found: " + routeId);
        }

        return tourGroupRepository
                .sumPriceByRouteIdAndStatusCompletedAndStartDateBetween(routeId, startDate, endDate);
    }

    @Transactional
    public TourGroupExpectationResponseDto updateExpectationStatus(
            final Long tourGroupId,
            final TourGroupExpectationRequestDto requestDto
    ) {
        final TourGroup tourGroup = tourGroupRepository.findById(tourGroupId)
                .orElseThrow(() -> new NotFoundException("Tour group not found: " + tourGroupId));

        tourGroup.setStartDate(requestDto.getStartDate());
        tourGroup.setEndDate(requestDto.getEndDate());
        tourGroup.setIsLegal(requestDto.getIsLegal());
        tourGroup.setContactData(requestDto.getContactData());

        final Long routeId = requestDto.getRouteId();

        tourGroup.setRoute(routeRepository.getReferenceById(routeId));

        if (requestDto.getIsLegal()) {
            final Long organizationId = requestDto.getOrganizationId();

            tourGroup.setOrganization(organizationRepository.getReferenceById(organizationId));
            tourGroup.setCustomerName(null);
        } else {
            tourGroup.setCustomerName(requestDto.getCustomerName());
            tourGroup.setOrganization(null);
        }

        final TourGroup savedTourGroup = tourGroupRepository.save(tourGroup);
        return tourGroupMapper.toCreateDto(savedTourGroup);
    }

    @Transactional
    public TourGroupExpectationResponseDto updateProcessingStatus(
            final Long tourGroupId,
            final TourGroupProcessingRequestDto requestDto
    ) {
        final TourGroup tourGroup = tourGroupRepository.findById(tourGroupId)
                .orElseThrow(() -> new NotFoundException("Tour group not found: " + tourGroupId));

        tourGroup.setStatus(TourGroupStatus.PROCESSING);

    }
}