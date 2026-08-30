package org.zapovednik.groupservice.mapper;

import org.mapstruct.Mapper;
import org.zapovednik.groupservice.dto.request.BankRequestDto;
import org.zapovednik.groupservice.dto.response.BankResponseDto;
import org.zapovednik.groupservice.model.entity.Bank;

@Mapper(componentModel = "spring")
public interface BankMapper {
    BankResponseDto toDto(final Bank bank);
    Bank toEntity(final BankRequestDto request);
}