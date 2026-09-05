package org.zapovednik.excursionservice.mapper;

import org.mapstruct.Mapper;
import org.zapovednik.excursionservice.dto.request.ResponsibleSpecialistRequestDto;
import org.zapovednik.excursionservice.dto.response.ResponsibleSpecialistResponseDto;
import org.zapovednik.excursionservice.model.entity.ResponsibleSpecialist;

@Mapper(componentModel = "spring")
public interface ResponsibleSpecialistMapper {
    ResponsibleSpecialistResponseDto toDto(final ResponsibleSpecialist responsibleSpecialist);
    ResponsibleSpecialist toEntity(final ResponsibleSpecialistRequestDto request);
}