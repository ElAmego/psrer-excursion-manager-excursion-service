package org.zapovednik.groupservice.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zapovednik.groupservice.model.entity.type.TourGroupStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourGroupResponseDto {
    private Long id;
    private TourGroupStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isLegal;
    private String contactData;

    private String customerName;

    private Long organizationId;
    private String organizationName;
    private String founderName;

    private Long driverId;
    private String driverName;

    private Long responsibleSpecialistId;
    private String responsibleSpecialistName;

    private Long routeId;
    private String routeName;
    private Integer routeNum;

    private List<GroupCountryResponseDto> countries;
}