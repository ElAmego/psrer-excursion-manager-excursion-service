package org.zapovednik.groupservice.model.repository.query;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TourGroupQuery {
    public static final String SUM_PRICE_BY_STATUS_AND_START_DATE_BETWEEN = """
        SELECT COALESCE(SUM(tg.price), 0) as totalPrice
        FROM TourGroup tg
        WHERE tg.status = :status
        AND tg.startDate BETWEEN :startDate AND :endDate
    """;

    public static final String SUM_PRICE_BY_IS_LEGAL_AND_STATUS_AND_START_DATE_BETWEEN = """
        SELECT COALESCE(SUM(tg.price), 0) as totalPrice
        FROM TourGroup tg
        WHERE tg.isLegal = :isLegal
        AND tg.status = :status
        AND tg.startDate BETWEEN :startDate AND :endDate
    """;

    public static final String SUM_PRICE_BY_ORGANIZATION_ID_AND_STATUS_AND_START_DATE_BETWEEN = """
        SELECT COALESCE(SUM(tg.price), 0) as totalPrice
        FROM TourGroup tg
        WHERE tg.organization.id = :organizationId
        AND tg.status = :status
        AND tg.startDate BETWEEN :startDate AND :endDate
    """;

    public static final String SUM_PRICE_BY_ROUTE_ID_AND_STATUS_COMPLETED_AND_START_DATE_BETWEEN = """
        SELECT COALESCE(SUM(tg.price), 0) as totalPrice
        FROM TourGroup tg
        WHERE tg.route.id = :routeId
        AND tg.status = 'COMPLETED'
        AND tg.startDate BETWEEN :startDate AND :endDate
    """;
}