package org.zapovednik.groupservice.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zapovednik.groupservice.model.entity.type.TourGroupStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourGroupUpdateRequestDto {
    @NotNull(message = "Status is required")
    private TourGroupStatus status;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Is legal is required")
    private Boolean isLegal;

    private Long organizationId;

    @Size(max = 50, message = "Customer name must not be exceed 50 characters")
    private String customerName;

    @NotEmpty(message = "Contact data is required")
    @Size(max = 50, message = "Contact data must not be exceed 50 characters")
    private String contactData;

    private Long responsibleSpecialistId;

    @NotNull(message = "Route id is required")
    private Long routeId;

    private Long driverId;

    @DecimalMin(value = "0.00", message = "Price must not be less than 0")
    @Digits(integer = 8, fraction = 2, message = "Price must have up to 8 integer digits and 2 decimal digits")
    private BigDecimal price;

    @DecimalMin(value = "0.00", message = "Export must not be less than 0")
    @Digits(integer = 8, fraction = 2, message = "Export must have up to 8 integer digits and 2 decimal digits")
    private BigDecimal export;

    private Boolean isPaid;

    private LocalDate contractDate;

    private Integer contractNumber;

    private Boolean isDocumentsSubmitted;
}