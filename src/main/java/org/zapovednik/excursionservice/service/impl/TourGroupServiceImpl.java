package org.zapovednik.excursionservice.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zapovednik.excursionservice.dto.request.TourGroupExpectationRequestDto;
import org.zapovednik.excursionservice.dto.request.TourGroupProcessingRequestDto;
import org.zapovednik.excursionservice.dto.request.TourGroupStatusUpdateRequestDto;
import org.zapovednik.excursionservice.dto.response.TourGroupExpectationResponseDto;
import org.zapovednik.excursionservice.dto.response.TourGroupResponseDto;
import org.zapovednik.excursionservice.exception.custom.NotFoundException;
import org.zapovednik.excursionservice.mapper.TourGroupMapper;
import org.zapovednik.excursionservice.model.entity.Driver;
import org.zapovednik.excursionservice.model.entity.Organization;
import org.zapovednik.excursionservice.model.entity.ResponsibleSpecialist;
import org.zapovednik.excursionservice.model.entity.Route;
import org.zapovednik.excursionservice.model.entity.TourGroup;
import org.zapovednik.excursionservice.model.entity.type.TourGroupStatus;
import org.zapovednik.excursionservice.model.repository.DriverRepository;
import org.zapovednik.excursionservice.model.repository.OrganizationRepository;
import org.zapovednik.excursionservice.model.repository.ResponsibleSpecialistRepository;
import org.zapovednik.excursionservice.model.repository.RouteRepository;
import org.zapovednik.excursionservice.model.repository.TourGroupRepository;
import org.zapovednik.excursionservice.service.TourGroupService;

@Service
@RequiredArgsConstructor
public class TourGroupServiceImpl implements TourGroupService {
    private final TourGroupRepository tourGroupRepository;
    private final RouteRepository routeRepository;
    private final OrganizationRepository organizationRepository;
    private final TourGroupMapper tourGroupMapper;
    private final DriverRepository driverRepository;
    private final ResponsibleSpecialistRepository responsibleSpecialistRepository;

    @Override
    @Transactional
    public Long save(final TourGroupExpectationRequestDto requestDto) {
        final TourGroup tourGroup = tourGroupMapper.toCreateEntity(requestDto);
        final Long routeId = requestDto.getRouteId();

        tourGroup.setStatus(TourGroupStatus.EXPECTATION);
        tourGroup.setRoute(routeRepository.getReferenceById(routeId));

        if (requestDto.getIsLegal()) {
            final Long organizationId = requestDto.getOrganizationId();

            tourGroup.setOrganization(organizationRepository.getReferenceById(organizationId));
        }

        final TourGroup savedTourGroup = tourGroupRepository.save(tourGroup);

        return savedTourGroup.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public TourGroupResponseDto findById(final Long tourGroupId) {
        final TourGroup tourGroup = tourGroupRepository.findById(tourGroupId)
                .orElseThrow(() -> new NotFoundException("Tour group not found: " + tourGroupId));

        return tourGroupMapper.toDto(tourGroup);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourGroupResponseDto> findAllByStatusIn(final List<TourGroupStatus> tourGroupStatuses) {
        final List<TourGroup> tourGroupList = tourGroupRepository.findAllByStatusIn(tourGroupStatuses);

        return tourGroupList.stream()
                .map(tourGroupMapper::toDto)
                .toList();
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public Long countByStatusAndStartDateBetween(
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return tourGroupRepository.countByStatusAndStartDateBetween(status, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return tourGroupRepository.countByIsLegalAndStatusAndStartDateBetween(isLegal, status, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal sumPriceByStatusAndStartDateBetween(
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return tourGroupRepository.sumPriceByStatusAndStartDateBetween(status, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal sumPriceByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return tourGroupRepository.sumPriceByIsLegalAndStatusAndStartDateBetween(isLegal, status, startDate, endDate);
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    @Transactional
    public TourGroupResponseDto updateTourGroupStatus(
            final Long tourGroupId,
            final TourGroupStatusUpdateRequestDto requestDto
    ) {
        final TourGroup tourGroup = tourGroupRepository.findById(tourGroupId)
                .orElseThrow(() -> new NotFoundException("Tour group not found: " + tourGroupId));

        final TourGroupStatus tourGroupStatus = requestDto.getStatus();

        tourGroup.setStatus(tourGroupStatus);

        return tourGroupMapper.toDto(tourGroup);
    }

    @Override
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
        final Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new NotFoundException("Route not found: " + routeId));

        tourGroup.setRoute(route);

        if (requestDto.getIsLegal()) {
            final Long organizationId = requestDto.getOrganizationId();
            final Organization organization = organizationRepository.findById(organizationId)
                    .orElseThrow(() -> new NotFoundException("Organization not found: " + organizationId));

            tourGroup.setOrganization(organization);
            tourGroup.setCustomerName(null);
        } else {
            tourGroup.setCustomerName(requestDto.getCustomerName());
            tourGroup.setOrganization(null);
        }

        return tourGroupMapper.toCreateDto(tourGroup);
    }

    @Override
    @Transactional
    public TourGroupResponseDto updateProcessingStatus(
            final Long tourGroupId,
            final TourGroupProcessingRequestDto requestDto
    ) {
        final TourGroup tourGroup = tourGroupRepository.findById(tourGroupId)
                .orElseThrow(() -> new NotFoundException("Tour group not found: " + tourGroupId));

        final Long responsibleSpecialistId = requestDto.getResponsibleSpecialistId();

        if (responsibleSpecialistId != null) {
            final ResponsibleSpecialist responsibleSpecialist = responsibleSpecialistRepository.findById(responsibleSpecialistId)
                    .orElseThrow(() -> new NotFoundException("Responsible specialist not found: " + responsibleSpecialistId));

            tourGroup.setResponsibleSpecialist(responsibleSpecialist);
        }

        final Long driverId = requestDto.getDriverId();

        if (driverId != null) {
            final Driver driver = driverRepository.findById(driverId)
                    .orElseThrow(() -> new NotFoundException("Driver not found: " + driverId));

            tourGroup.setDriver(driver);
        }

        tourGroup.setPrice(requestDto.getPrice());
        tourGroup.setExport(requestDto.getExport());
        tourGroup.setIsPaid(requestDto.getIsPaid());
        tourGroup.setContractDate(requestDto.getContractDate());
        tourGroup.setContractNumber(requestDto.getContractNumber());
        tourGroup.setIsDocumentsSubmitted(requestDto.getIsDocumentsSubmitted());

        return tourGroupMapper.toDto(tourGroup);
    }
}