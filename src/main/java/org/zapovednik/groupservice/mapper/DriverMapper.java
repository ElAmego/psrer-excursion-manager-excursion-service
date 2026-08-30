package org.zapovednik.groupservice.mapper;

import org.mapstruct.Mapper;
import org.zapovednik.groupservice.dto.request.DriverRequestDto;
import org.zapovednik.groupservice.dto.response.DriverResponseDto;
import org.zapovednik.groupservice.model.entity.Driver;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    DriverResponseDto toDto(final Driver driver);
    Driver toEntity(final DriverRequestDto request);
}