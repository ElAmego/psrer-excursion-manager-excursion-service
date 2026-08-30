package org.zapovednik.groupservice.model.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zapovednik.groupservice.model.entity.TourGroup;
import org.zapovednik.groupservice.model.entity.type.TourGroupStatus;
import org.zapovednik.groupservice.model.repository.query.TourGroupQuery;

public interface TourGroupRepository extends JpaRepository<TourGroup, Long> {
    List<TourGroup> findAllByStatusIn(
            final List<TourGroupStatus> statuses
    );

    List<TourGroup> findAllByStartDateBetweenAndStatusIn(
            final LocalDate startDate,
            final LocalDate endDate,
            final List<TourGroupStatus> statuses
    );

    List<TourGroup> findAllByStartDateBetweenAndOrganizationIdAndStatusIn(
            final LocalDate startDate,
            final LocalDate endDate,
            final Long organizationId,
            final List<TourGroupStatus> statuses
    );


    List<TourGroup> findAllByStartDateBetweenAndStatusCompletedAndIsPaidTrue(
            final LocalDate startDate,
            final LocalDate endDate
    );

    List<TourGroup> findAllByStartDateBetweenAndStatusCompletedAndIsPaidFalse(
            final LocalDate startDate,
            final LocalDate endDate
    );

    List<TourGroup> findAllByStartDateBetweenAndStatusCompletedAndIsDocumentsSubmittedTrue(
            final LocalDate startDate,
            final LocalDate endDate
    );

    List<TourGroup> findAllByStartDateBetweenAndStatusCompletedAndIsDocumentsSubmittedFalse(
            final LocalDate startDate,
            final LocalDate endDate
    );

    // --------------------------------- Статистика ---------------------------------

    // Статистика общая: кол-во групп принято или отменено
    Long countByStatusAndStartDateBetween(
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );

    // Статистика общая: кол-во групп в рамках физ. или юр. лиц принято или отменено кол-во групп
    Long countByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );

    // Статистика общая: получено или потеряно денег
    @Query(value = TourGroupQuery.SUM_PRICE_BY_STATUS_AND_START_DATE_BETWEEN)
    BigDecimal sumPriceByStatusAndStartDateBetween(
            @Param("status") final TourGroupStatus status,
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate
    );

    // Статистика общая: получено или потеряно денег в рамках физ. или юр. лиц
    @Query(value = TourGroupQuery.SUM_PRICE_BY_IS_LEGAL_AND_STATUS_AND_START_DATE_BETWEEN)
    BigDecimal sumPriceByIsLegalAndStatusAndStartDateBetween(
            @Param("isLegal") final Boolean isLegal,
            @Param("status") final TourGroupStatus status,
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate
    );

    // Статистика по юр. лицам индивидуальная: принято или отменено кол-во групп
    Long countByOrganizationIdAndStatusAndStartDateBetween(
            final Long organizationId,
            final TourGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );

    // Статистика по юр. лицам индивидуальная: получено или потеряно денег
    @Query(value = TourGroupQuery.SUM_PRICE_BY_ORGANIZATION_ID_AND_STATUS_AND_START_DATE_BETWEEN)
    BigDecimal sumPriceByOrganizationIdAndStatusAndStartDateBetween(
            @Param("organizationId") final Long organizationId,
            @Param("status") final TourGroupStatus status,
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate
    );

    // Статистика по водителям: кол-во групп откатали (status -> Completed)
    Long countByDriverIdAndStatusCompletedAndStartDateBetween(
            final Long driverId,
            final LocalDate startDate,
            final LocalDate endDate
    );

    // Статистика по сопровождающим: кол-во групп было сопровождено (status -> Completed)
    Long countByResponsibleSpecialistIdAndStatusCompletedAndStartDateBetween(
            final Long responsibleSpecialistId,
            final LocalDate startDate,
            final LocalDate endDate
    );

    // Статистика по маршрутам: кол-во групп было принято (status -> Completed)
    Long countByRouteIdAndStatusCompletedAndStartDateBetween(
            final Long routeId,
            final LocalDate startDate,
            final LocalDate endDate
    );

    // Статистика по маршрутам: получено денег (status -> Completed)
    @Query(value = TourGroupQuery.SUM_PRICE_BY_ROUTE_ID_AND_STATUS_COMPLETED_AND_START_DATE_BETWEEN)
    BigDecimal sumPriceByRouteIdAndStatusCompletedAndStartDateBetween(
            @Param("routeId") final Long routeId,
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate
    );
}