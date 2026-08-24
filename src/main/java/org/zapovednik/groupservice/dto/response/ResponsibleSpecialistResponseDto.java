package org.zapovednik.groupservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsibleSpecialistResponseDto {
    private Long id;
    private String responsibleSpecialistName;
    private String responsibleSpecialistPhoneNumber;
}