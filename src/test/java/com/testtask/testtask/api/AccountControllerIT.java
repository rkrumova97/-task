package com.testtask.testtask.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.testtask.testtask.TestTaskApplication;
import com.testtask.testtask.api.dto.AccountDto;
import com.testtask.testtask.model.Account;
import com.testtask.testtask.repository.AccountRepository;
import com.testtask.testtask.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@EnableSpringDataWebSupport
@SpringBootTest(classes = TestTaskApplication.class)
public class AccountControllerIT {
    private final String FIRST_NAME = "Rumy";
    private final String FIRST_NAME_UPDATED = "Mimy";
    private final String LAST_NAME = "Krumova";
    private final String EMAIL = "rkrumova97@gmail.com";
    private final LocalDate BIRTHDAY = LocalDate.now();

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private AccountService accountService;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @PersistenceContext
    private EntityManager em;

    private MockMvc restAccountMockMvc;

    private Account account;

    private AccountDto accountDto;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccountController accountController = new AccountController(accountService);
        this.restAccountMockMvc = MockMvcBuilders
                .standaloneSetup(accountController)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .build();
        em.flush();

        mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModule(new JavaTimeModule());
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
    }

    /**
     * Create an entity for this test.
     */
    public Account createEntity() {

        return Account.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .birthday(BIRTHDAY)
                .email(EMAIL)
                .build();
    }

    /**
     * Create a DTO for this test.
     */
    public AccountDto createDTO() {

        return AccountDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .birthday(BIRTHDAY)
                .email(EMAIL)
                .build();
    }

    @BeforeEach
    public void initTest() {
        account = createEntity();
        accountDto = createDTO();
    }

    @Test
    @Transactional
    public void createAccount() throws Exception {
        int databaseSizeBeforeCreate = accountRepository.findAll().size();

        String requestJson = mapper.writeValueAsString(accountDto);

        // Create the Account
        restAccountMockMvc.perform(
                post("/create")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated());

        // Validate the Account in the database
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts).hasSize(databaseSizeBeforeCreate + 1);
        account = accounts.get(accounts.size() - 1);
        assertThat(account.getBirthday()).isEqualTo(BIRTHDAY);
        assertThat(account.getEmail()).isEqualTo(EMAIL);
        assertThat(account.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(account.getLastName()).isEqualTo(LAST_NAME);
    }

    @Test
    @Transactional
    public void updateAccount() throws Exception {
        accountRepository.save(account);
        int databaseSizeBeforeUpdate = accountRepository.findAll().size();
        accountDto.setFirstName(FIRST_NAME_UPDATED);
        accountDto.setId(account.getId());

        String requestJson = mapper.writeValueAsString(accountDto);

        // Update the Account
        restAccountMockMvc.perform(
                put("/update")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isNoContent());

        // Validate the Account in the database
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts).hasSize(databaseSizeBeforeUpdate);
        Account testBenefit = accounts.get(accounts.size() - 1);
        assertThat(testBenefit.getBirthday()).isEqualTo(BIRTHDAY);
        assertThat(testBenefit.getEmail()).isEqualTo(EMAIL);
        assertThat(testBenefit.getFirstName()).isEqualTo(FIRST_NAME_UPDATED);
        assertThat(testBenefit.getLastName()).isEqualTo(LAST_NAME);
    }

    @Test
    @Transactional
    public void deleteAccount() throws Exception {
        accountRepository.save(account);
        int databaseSizeBeforeDelete = accountRepository.findAll().size();

        // Delete the Account
        restAccountMockMvc.perform(
                delete("/delete/{id}", account.getId()))
                .andExpect(status().isNoContent());

        // Validate the Account is deleted
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void findAllAccounts() throws Exception {
        accountRepository.save(account);

        restAccountMockMvc.perform(get("/get?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").value(hasItem(account.getId().toString())))
                .andExpect(jsonPath("$.[*].firstName").value(hasItem(FIRST_NAME)))
                .andExpect(jsonPath("$.[*].lastName").value(hasItem(LAST_NAME)))
                .andExpect(jsonPath("$.[*].email").value(hasItem(EMAIL)));
    }
}
