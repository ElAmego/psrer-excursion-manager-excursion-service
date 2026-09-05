package org.zapovednik.excursionservice.service;

import java.time.LocalDate;
import java.util.List;
import org.zapovednik.excursionservice.dto.request.GroupCountryRequestDto;
import org.zapovednik.excursionservice.dto.response.GroupCountryResponseDto;
import org.zapovednik.excursionservice.model.entity.type.TourGroupStatus;
import org.zapovednik.excursionservice.model.repository.projection.CountryStatisticsProjection;

public interface GroupCountryService {
    Long save(final Long tourGroupId, final GroupCountryRequestDto requestDto);
    Long sumParticipantQuantityByStatusAndStartDateBetween(
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    Long sumParticipantQuantityByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    List<CountryStatisticsProjection> findCountryStatisticsByStatusCompletedAndStartDateBetween(
            final LocalDate startDate,
            final LocalDate endDate
    );
    Long sumParticipantQuantityByOrganizationIdAndStatusAndStartDateBetween(
            final Long organizationId,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    Long sumParticipantQuantityByRouteIdAndStatusCompletedAndStartDateBetween(
            final Long routeId,
            final LocalDate startDate,
            final LocalDate endDate
    );

    GroupCountryResponseDto update(final Long groupCountryId, final GroupCountryRequestDto requestDto);
}