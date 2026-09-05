package org.zapovednik.groupservice.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zapovednik.groupservice.dto.request.BankRequestDto;
import org.zapovednik.groupservice.dto.response.BankResponseDto;
import org.zapovednik.groupservice.exception.custom.NotFoundException;
import org.zapovednik.groupservice.mapper.BankMapper;
import org.zapovednik.groupservice.model.entity.Bank;
import org.zapovednik.groupservice.model.repository.BankRepository;
import org.zapovednik.groupservice.service.BankService;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    @Override
    @Transactional
    public Long create(final BankRequestDto requestDto) {
        final Bank bank = bankMapper.toEntity(requestDto);
        final Bank savedBank = bankRepository.save(bank);

        return savedBank.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public BankResponseDto findById(final Long bankId) {
        final Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new NotFoundException("Bank not found: " + bankId));

        return bankMapper.toDto(bank);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankResponseDto> findAll() {
        final List<Bank> bankList = bankRepository.findAll();

        return bankList.stream()
                .map(bankMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public BankResponseDto updateById(final Long bankId, final BankRequestDto requestDto) {
        final Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new NotFoundException("Bank not found: " + bankId));

        bank.setBankName(requestDto.getBankName());
        bank.setBic(requestDto.getBic());

        return bankMapper.toDto(bank);
    }

    @Override
    @Transactional
    public void deleteById(final Long bankId) {
        if (bankRepository.existsById(bankId)) {
            bankRepository.deleteById(bankId);
        } else {
            throw new NotFoundException("Bank not found: " + bankId);
        }
    }
}