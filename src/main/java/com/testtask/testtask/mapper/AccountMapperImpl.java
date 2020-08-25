package com.testtask.testtask.mapper;

import com.testtask.testtask.api.dto.AccountDto;
import com.testtask.testtask.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toEntity(AccountDto accountDto) {
        return Account.builder()
                .firstName(accountDto.getFirstName())
                .lastName(accountDto.getLastName())
                .email(accountDto.getEmail())
                .birthday(accountDto.getBirthday())
                .build();
    }

    @Override
    public AccountDto toDto(Account account) {
        return AccountDto.builder()
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .birthday(account.getBirthday())
                .build();
    }
}
