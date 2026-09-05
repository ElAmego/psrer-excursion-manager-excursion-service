package org.zapovednik.groupservice.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.zapovednik.groupservice.dto.request.TourGroupExpectationRequestDto;
import org.zapovednik.groupservice.dto.request.TourGroupProcessingRequestDto;
import org.zapovednik.groupservice.dto.request.TourGroupStatusUpdateRequestDto;
import org.zapovednik.groupservice.dto.response.TourGroupExpectationResponseDto;
import org.zapovednik.groupservice.dto.response.TourGroupResponseDto;
import org.zapovednik.groupservice.model.entity.type.TourGroupStatus;

public interface TourGroupService {
    Long save(final TourGroupExpectationRequestDto requestDto);
    TourGroupResponseDto findById(final Long tourGroupId);
    List<TourGroupResponseDto> findAllByStatusIn(final List<TourGroupStatus> tourGroupStatuses);
    List<TourGroupResponseDto> findAllByStartDateBetweenAndStatusIn(
            final LocalDate startDate,
            final LocalDate endDate,
            final List<TourGroupStatus> tourGroupStatuses
    );
    List<TourGroupResponseDto> findAllByStartDateBetweenAndOrganizationIdAndStatusIn(
            final LocalDate startDate,
            final LocalDate endDate,
            final Long organizationId,
            final List<TourGroupStatus> tourGroupStatuses
    );
    List<TourGroupResponseDto> findAllByStartDateBetweenAndStatusCompletedAndIsPaidTrue(
            final LocalDate startDate,
            final LocalDate endDate
    );
    List<TourGroupResponseDto> findAllByStartDateBetweenAndStatusCompletedAndIsPaidFalse(
            final LocalDate startDate,
            final LocalDate endDate
    );
    List<TourGroupResponseDto> findAllByStartDateBetweenAndStatusCompletedAndIsDocumentsSubmittedTrue(
            final LocalDate startDate,
            final LocalDate endDate
    );
    List<TourGroupResponseDto> findAllByStartDateBetweenAndStatusCompletedAndIsDocumentsSubmittedFalse(
            final LocalDate startDate,
            final LocalDate endDate
    );
    Long countByStatusAndStartDateBetween(
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    Long countByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    BigDecimal sumPriceByStatusAndStartDateBetween(
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    BigDecimal sumPriceByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    Long countByOrganizationIdAndStatusAndStartDateBetween(
            final Long organizationId,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    BigDecimal sumPriceByOrganizationIdAndStatusAndStartDateBetween(
            final Long organizationId,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    Long countByDriverIdAndStatusCompletedAndStartDateBetween(
            final Long driverId,
            final LocalDate startDate,
            final LocalDate endDate
    );
    Long countByResponsibleSpecialistIdAndStatusCompletedAndStartDateBetween(
            final Long responsibleSpecialistId,
            final LocalDate startDate,
            final LocalDate endDate
    );
    Long countByRouteIdAndStatusCompletedAndStartDateBetween(
            final Long routeId,
            final LocalDate startDate,
            final LocalDate endDate
    );
    BigDecimal sumPriceByRouteIdAndStatusCompletedAndStartDateBetween(
            final Long routeId,
            final LocalDate startDate,
            final LocalDate endDate
    );
    TourGroupResponseDto updateTourGroupStatus(
            final Long tourGroupId,
            final TourGroupStatusUpdateRequestDto requestDto
    );
    TourGroupExpectationResponseDto updateExpectationStatus(
            final Long tourGroupId,
            final TourGroupExpectationRequestDto requestDto
    );
    TourGroupResponseDto updateProcessingStatus(
            final Long tourGroupId,
            final TourGroupProcessingRequestDto requestDto
    );
}