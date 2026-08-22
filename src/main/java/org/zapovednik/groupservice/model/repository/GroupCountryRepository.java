package org.zapovednik.groupservice.model.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zapovednik.groupservice.model.entity.GroupCountry;
import org.zapovednik.groupservice.model.entity.type.TourGroupStatus;
import org.zapovednik.groupservice.model.repository.projection.CountryStatisticsProjection;
import org.zapovednik.groupservice.model.repository.query.GroupCountryQuery;

public interface GroupCountryRepository extends JpaRepository<GroupCountry, Long> {

    // Статистика общая: кол-во людей принято или отменено
    @Query(value = GroupCountryQuery.SUM_PARTICIPANT_QUANTITY_BY_STATUS_AND_START_DATE_BETWEEN)
    Long sumParticipantQuantityByStatusAndStartDateBetween(
            @Param("status") final TourGroupStatus status,
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate
    );

    // Статистика общая: кол-во людей в рамках физ. или юр. лиц принято или отменено
    @Query(value = GroupCountryQuery.SUM_PARTICIPANT_QUANTITY_BY_IS_LEGAL_AND_STATUS_AND_START_DATE_BETWEEN)
    Long sumParticipantQuantityByIsLegalAndStatusAndStartDateBetween(
            @Param("isLegal") final Boolean isLegal,
            @Param("status") final TourGroupStatus status,
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate
    );

    // Статистика общая: кол-во уникальных стран (name и code) и кол-во человек для каждой страны (status Completed)
    @Query(value = GroupCountryQuery.FIND_COUNTRY_STATISTICS_BY_STATUS_COMPLETED_AND_START_DATE_BETWEEN)
    List<CountryStatisticsProjection> findCountryStatisticsByStatusCompletedAndStartDateBetween(
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate
    );

    // Статистика по юр. лицам индивидуальная: принято или отменено кол-во людей
    @Query(value = GroupCountryQuery.SUM_PARTICIPANT_QUANTITY_BY_ORGANIZATION_ID_AND_STATUS_AND_START_DATE_BETWEEN)
    Long sumParticipantQuantityByOrganizationIdAndStatusAndStartDateBetween(
            @Param("organizationId") final Long organizationId,
            @Param("status") final TourGroupStatus status,
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate
    );

    // Статистика по маршрутам: кол-во людей принято (status -> Completed)
    @Query(value = GroupCountryQuery.SUM_PARTICIPANT_QUANTITY_BY_ROUTE_ID_AND_STATUS_COMPLETED_AND_START_DATE_BETWEEN)
    Long sumParticipantQuantityByRouteIdAndStatusCompletedAndStartDateBetween(
            @Param("routeId") final Long routeId,
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate
    );
}