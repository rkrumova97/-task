package com.testtask.testtask.api;

import com.testtask.testtask.api.dto.AccountDto;
import com.testtask.testtask.model.Account;
import com.testtask.testtask.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@CrossOrigin
@RestController
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(Pageable pageable) {
        List<Account> accountList = accountService.findAllAccounts(pageable.getSort());
        return ResponseEntity.ok().body(accountList);
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody AccountDto accountDto) throws URISyntaxException {
        Account account = accountService.saveAccount(accountDto);
        return ResponseEntity.created(new URI("/api/" + account.getId()))
                .body(account);
    }

    @PutMapping
    public ResponseEntity<Account> update(@RequestBody AccountDto accountDto) {
        Account account = accountService.updateAccount(accountDto);
        if (Objects.nonNull(account)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(account);
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid) {
        accountService.deleteAccount(uuid);
        return ResponseEntity.noContent().build();
    }
}
