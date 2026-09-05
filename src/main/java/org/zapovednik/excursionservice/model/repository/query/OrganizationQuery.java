package org.zapovednik.excursionservice.model.repository.query;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrganizationQuery {
    public static final String FIND_TOP_3_ORGANIZATIONS_BY_GROUP_COUNT = """
            SELECT o.organizationName as organizationName, COUNT(tg.id) as groupCount
            FROM Organization o
            JOIN TourGroup tg ON tg.organization = o
            WHERE tg.isLegal = true
            AND tg.status = 'COMPLETED'
            AND tg.startDate BETWEEN :startDate AND :endDate
            GROUP BY o.id, o.organizationName
            ORDER BY groupCount DESC
    """;

    public static final String FIND_TOP_3_ORGANIZATIONS_BY_PARTICIPANT_QUANTITY_SUM = """
            SELECT o.organizationName as organizationName, SUM(gc.participantQuantity) as participantQuantitySum
            FROM Organization o
            JOIN TourGroup tg ON tg.organization = o
            JOIN GroupCountry gc ON gc.tourGroup = tg
            WHERE tg.isLegal = true
            AND tg.status = 'COMPLETED'
            AND tg.startDate BETWEEN :startDate AND :endDate
            GROUP BY o.id, o.organizationName
            ORDER BY participantQuantitySum DESC
    """;
}