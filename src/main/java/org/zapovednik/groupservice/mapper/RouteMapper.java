package org.zapovednik.groupservice.mapper;

import org.mapstruct.Mapper;
import org.zapovednik.groupservice.dto.request.RouteRequestDto;
import org.zapovednik.groupservice.dto.response.RouteResponseDto;
import org.zapovednik.groupservice.model.entity.Route;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    RouteResponseDto toDto(final Route route);
    Route toEntity(final RouteRequestDto request);
}