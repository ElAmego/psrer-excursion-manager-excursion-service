package org.zapovednik.excursionservice.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zapovednik.excursionservice.model.entity.type.TourGroupStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourGroupExpectationResponseDto {
    private Long id;
    private TourGroupStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isLegal;
    private String contactData;

    private Long routeId;
    private String routeName;
    private Integer routeNum;

    private String customerName;

    private Long organizationId;
    private String organizationName;
    private String founderName;
}