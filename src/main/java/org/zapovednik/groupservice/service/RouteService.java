package org.zapovednik.groupservice.service;

import java.util.List;
import org.zapovednik.groupservice.dto.request.RouteRequestDto;
import org.zapovednik.groupservice.dto.response.RouteResponseDto;

public interface RouteService {
    Long save(final RouteRequestDto requestDto);
    RouteResponseDto findById(final Long routeId);
    List<RouteResponseDto> findAll();
    RouteResponseDto updateById(final Long routeId, final RouteRequestDto requestDto);
    void deleteById(final Long routeId);
}