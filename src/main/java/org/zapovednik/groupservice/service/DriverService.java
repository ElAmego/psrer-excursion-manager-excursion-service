package org.zapovednik.groupservice.service;

import java.util.List;
import org.zapovednik.groupservice.dto.request.DriverRequestDto;
import org.zapovednik.groupservice.dto.response.DriverResponseDto;

public interface DriverService {
    Long save(final DriverRequestDto requestDto);
    DriverResponseDto findById(final Long driverId);
    List<DriverResponseDto> findAll();
    DriverResponseDto updateById(final Long driverId, final DriverRequestDto requestDto);
    void deleteById(final Long driverId);
}