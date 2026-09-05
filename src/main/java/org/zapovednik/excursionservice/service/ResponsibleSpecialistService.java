package org.zapovednik.excursionservice.service;

import java.util.List;
import org.zapovednik.excursionservice.dto.request.ResponsibleSpecialistRequestDto;
import org.zapovednik.excursionservice.dto.response.ResponsibleSpecialistResponseDto;

public interface ResponsibleSpecialistService {
    Long save(final ResponsibleSpecialistRequestDto requestDto);
    ResponsibleSpecialistResponseDto findById(final Long responsibleSpecialistId);
    List<ResponsibleSpecialistResponseDto> findAll();
    ResponsibleSpecialistResponseDto updateById(final Long responsibleSpecialistId, final ResponsibleSpecialistRequestDto requestDto);
    void deleteById(final Long responsibleSpecialistId);
}