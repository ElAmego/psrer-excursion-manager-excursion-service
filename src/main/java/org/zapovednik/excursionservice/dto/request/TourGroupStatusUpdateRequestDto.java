package org.zapovednik.excursionservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zapovednik.excursionservice.model.entity.type.TourGroupStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourGroupStatusUpdateRequestDto {
    @NotNull(message = "Status is required")
    private TourGroupStatus status;
}