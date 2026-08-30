package org.zapovednik.groupservice.mapper;

import org.mapstruct.Mapper;
import org.zapovednik.groupservice.dto.request.ResponsibleSpecialistRequestDto;
import org.zapovednik.groupservice.dto.response.ResponsibleSpecialistResponseDto;
import org.zapovednik.groupservice.model.entity.ResponsibleSpecialist;

@Mapper(componentModel = "spring")
public interface ResponsibleSpecialistMapper {
    ResponsibleSpecialistResponseDto toDto(final ResponsibleSpecialist responsibleSpecialist);
    ResponsibleSpecialist toEntity(final ResponsibleSpecialistRequestDto request);
}