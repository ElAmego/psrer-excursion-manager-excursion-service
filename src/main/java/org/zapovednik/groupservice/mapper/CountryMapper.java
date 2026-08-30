package org.zapovednik.groupservice.mapper;

import org.mapstruct.Mapper;
import org.zapovednik.groupservice.dto.request.CountryRequestDto;
import org.zapovednik.groupservice.dto.response.CountryResponseDto;
import org.zapovednik.groupservice.model.entity.Country;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryResponseDto toDto(final Country country);
    Country toEntity(final CountryRequestDto request);
}