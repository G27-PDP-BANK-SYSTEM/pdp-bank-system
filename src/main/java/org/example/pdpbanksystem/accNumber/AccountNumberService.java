package org.example.pdpbanksystem.accNumber;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.pdpbanksystem.accNumber.dto.AccountNumberCreateDto;
import org.example.pdpbanksystem.accNumber.dto.AccountNumberResponseDto;
import org.example.pdpbanksystem.accNumber.dto.AccountNumberUpdateDto;
import org.example.pdpbanksystem.accNumber.entity.AccountNumber;
import org.example.pdpbanksystem.common.rsql.SpecificationBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Random;


@Service
@RequiredArgsConstructor
@Transactional
public class AccountNumberService {

    private final AccountNumberRepository accountNumberRepository;

    ModelMapper modelMapper = new ModelMapper();

    public AccountNumberResponseDto create() {

        AccountNumberCreateDto createDto = new AccountNumberCreateDto();

        AccountNumber map = modelMapper.map(createDto, AccountNumber.class);
        map.setAccountNumber(AccountNumberGeneration());
        accountNumberRepository.save(map);
        return modelMapper.map(map, AccountNumberResponseDto.class);


    }

    private static String AccountNumberGeneration() {
        Random random = new Random();
        StringBuilder str = new StringBuilder("2020");
        for (int i = 0; i < 16; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }


    public Page<AccountNumberResponseDto> getAllUser(Pageable pageable, String predicate) {

        Specification<AccountNumber> build = SpecificationBuilder.build(predicate);

        Page<AccountNumber> resultPage;

        if (build == null) {
            resultPage = accountNumberRepository.findAll(pageable);
        } else {
            resultPage = accountNumberRepository.findAll(build, pageable);
        }

        return resultPage.map(entity -> {
            AccountNumberResponseDto dto = modelMapper.map(entity, AccountNumberResponseDto.class);
            return dto;
        });
    }


    public AccountNumberResponseDto get(Integer id) {

        AccountNumber accountNumber = accountNumberRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        return modelMapper.map(accountNumber, AccountNumberResponseDto.class);

    }


    public void delete(Integer id) {

        if (!accountNumberRepository.existsById(id)) {
            throw new EntityNotFoundException(String.valueOf(id));
        }
        accountNumberRepository.deleteById(id);

    }

    public AccountNumberResponseDto update(Integer id, @Valid @RequestBody AccountNumberUpdateDto updateDto) {
        AccountNumber accountNumber1 = accountNumberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        String accountNumber = updateDto.getAccountNumber();
        accountNumber1.setAccountNumber(accountNumber);
        accountNumberRepository.save(accountNumber1);
        return modelMapper.map(accountNumber1, AccountNumberResponseDto.class);

    }

}
