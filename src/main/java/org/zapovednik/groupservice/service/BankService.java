package org.zapovednik.groupservice.service;

import java.util.List;
import org.zapovednik.groupservice.dto.request.BankRequestDto;
import org.zapovednik.groupservice.dto.response.BankResponseDto;

public interface BankService {
    Long create(final BankRequestDto requestDto);
    BankResponseDto findById(final Long bankId);
    List<BankResponseDto> findAll();
    BankResponseDto updateById(final Long bankId, final BankRequestDto requestDto);
    void deleteById(final Long bankId);
}