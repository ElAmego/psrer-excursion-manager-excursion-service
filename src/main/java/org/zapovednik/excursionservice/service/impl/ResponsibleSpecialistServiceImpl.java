package org.zapovednik.excursionservice.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zapovednik.excursionservice.dto.request.ResponsibleSpecialistRequestDto;
import org.zapovednik.excursionservice.dto.response.ResponsibleSpecialistResponseDto;
import org.zapovednik.excursionservice.exception.custom.NotFoundException;
import org.zapovednik.excursionservice.mapper.ResponsibleSpecialistMapper;
import org.zapovednik.excursionservice.model.entity.ResponsibleSpecialist;
import org.zapovednik.excursionservice.model.repository.ResponsibleSpecialistRepository;
import org.zapovednik.excursionservice.service.ResponsibleSpecialistService;

@Service
@RequiredArgsConstructor
public class ResponsibleSpecialistServiceImpl implements ResponsibleSpecialistService {
    private final ResponsibleSpecialistRepository responsibleSpecialistRepository;
    private final ResponsibleSpecialistMapper responsibleSpecialistMapper;

    @Override
    @Transactional
    public Long save(final ResponsibleSpecialistRequestDto requestDto) {
        final ResponsibleSpecialist responsibleSpecialist = responsibleSpecialistMapper.toEntity(requestDto);
        final ResponsibleSpecialist savedResponsibleSpecialist = responsibleSpecialistRepository
                .save(responsibleSpecialist);

        return savedResponsibleSpecialist.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponsibleSpecialistResponseDto findById(final Long responsibleSpecialistId) {
        final ResponsibleSpecialist responsibleSpecialist = responsibleSpecialistRepository
                .findById(responsibleSpecialistId)
                .orElseThrow(() -> new NotFoundException("ResponsibleSpecialist not found: " + responsibleSpecialistId));

        return responsibleSpecialistMapper.toDto(responsibleSpecialist);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponsibleSpecialistResponseDto> findAll() {
        final List<ResponsibleSpecialist> responsibleSpecialistList = responsibleSpecialistRepository.findAll();

        return responsibleSpecialistList.stream()
                .map(responsibleSpecialistMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public ResponsibleSpecialistResponseDto updateById(
            final Long responsibleSpecialistId,
            final ResponsibleSpecialistRequestDto requestDto
    ) {
        final ResponsibleSpecialist responsibleSpecialist = responsibleSpecialistRepository
                .findById(responsibleSpecialistId)
                .orElseThrow(() -> new NotFoundException("ResponsibleSpecialist not found: " + responsibleSpecialistId));

        responsibleSpecialist.setResponsibleSpecialistName(requestDto.getResponsibleSpecialistName());
        responsibleSpecialist.setResponsibleSpecialistPhoneNumber(requestDto.getResponsibleSpecialistPhoneNumber());

        return responsibleSpecialistMapper.toDto(responsibleSpecialist);
    }

    @Override
    @Transactional
    public void deleteById(final Long responsibleSpecialistId) {
        if (responsibleSpecialistRepository.existsById(responsibleSpecialistId)) {
            responsibleSpecialistRepository.deleteById(responsibleSpecialistId);
        } else {
            throw new NotFoundException("ResponsibleSpecialist not found: " + responsibleSpecialistId);
        }
    }
}