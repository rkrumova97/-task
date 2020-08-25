package com.testtask.testtask.service;

import com.testtask.testtask.api.dto.AccountDto;
import com.testtask.testtask.mapper.AccountMapper;
import com.testtask.testtask.model.Account;
import com.testtask.testtask.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account saveAccount(AccountDto accountDto) {
        return accountRepository.save(accountMapper.toEntity(accountDto));
    }

    @Override
    public void deleteAccount(UUID uuid) {
        accountRepository.deleteById(uuid);
    }

    @Override
    public List<Account> findAllAccounts(Sort sort) {
        return accountRepository.findAll(sort);
    }

    @Override
    public Account updateAccount(AccountDto accountDto) {
        Optional<Account> optionalAccount = accountRepository.findById(accountDto.getId());
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            account.setBirthday(accountDto.getBirthday());
            account.setEmail(accountDto.getEmail());
            account.setFirstName(accountDto.getFirstName());
            account.setLastName(accountDto.getLastName());
            return accountRepository.save(account);
        } else return null;
    }
}
