package org.zapovednik.groupservice.service;

import java.util.List;
import org.zapovednik.groupservice.dto.request.CountryRequestDto;
import org.zapovednik.groupservice.dto.response.CountryResponseDto;

public interface CountryService {
    Long save(final CountryRequestDto requestDto);
    CountryResponseDto findById(final Long countryId);
    List<CountryResponseDto> findAll();
    CountryResponseDto updateById(final Long countryId, final CountryRequestDto requestDto);
    void deleteById(final Long countryId);
}