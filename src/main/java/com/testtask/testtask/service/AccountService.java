package com.testtask.testtask.service;

import com.testtask.testtask.api.dto.AccountDto;
import com.testtask.testtask.model.Account;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    public Account saveAccount(AccountDto accountDto);

    public void deleteAccount(UUID uuid);

    public List<Account> findAllAccounts(Sort sort);

    Account updateAccount(AccountDto accountDto);
}
