package com.testtask.testtask.mapper;

import com.testtask.testtask.api.dto.AccountDto;
import com.testtask.testtask.model.Account;
import org.springframework.stereotype.Component;

public interface AccountMapper {
    public Account toEntity(AccountDto accountDto);

    public AccountDto toDto(Account account);
}
