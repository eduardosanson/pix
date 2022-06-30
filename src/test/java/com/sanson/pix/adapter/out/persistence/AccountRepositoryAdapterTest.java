package com.sanson.pix.adapter.out.persistence;

import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.Holder;
import com.sanson.pix.domain.managerPix.HolderType;
import com.sanson.pix.domain.managerPix.pixKeys.Email;
import com.sanson.pix.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AccountRepositoryAdapterTest {

    private AccountRepositoryAdapter accountRepositoryAdapter;

    @Autowired
    private AccountRepository accountRepository;

    public Holder validHolderPF = new Holder("holderTest",null, HolderType.F);

    public Account validAccount = new Account(AccountType.CHECKING,
            1234,123456,validHolderPF, Arrays.asList(new Email("teste@teste.com")));

    @BeforeEach
    public void init(){
        accountRepositoryAdapter = new AccountRepositoryAdapter(accountRepository);
    }

    @Test
    public void shouldSaveAccount(){
        var account = accountRepositoryAdapter.save(validAccount);
        assertEquals(validAccount.getId(), account.getId());
        assertEquals(validAccount.getAgency(), account.getAgency());
        assertEquals(validAccount.getNumber(), account.getNumber());
        assertEquals(validAccount.getType(), account.getType());
        assertEquals(validAccount.getHolder().getName(), account.getHolder().getName());
        assertEquals(validAccount.getHolder().getLastName(), account.getHolder().getLastName());
        assertEquals(validAccount.getHolder().getType(), account.getHolder().getType());
        assertEquals(validAccount.getPixKeys(), account.getPixKeys());
    }

    @Test
    public void shouldFindByAgencyAndNumber(){
        accountRepositoryAdapter.save(validAccount);
        assertNotNull(accountRepositoryAdapter.findBy(validAccount.getAgency(), validAccount.getNumber()));
    }

    @Test
    public void shouldFindByAccountId(){
        accountRepositoryAdapter.save(validAccount);
        assertNotNull(accountRepositoryAdapter.load(validAccount.getId()));
    }

    @Test
    public void shouldFindByPixKeyId(){
        accountRepositoryAdapter.save(validAccount);
        var pixKey = validAccount.getPixKeys().get(0);
        var account = accountRepositoryAdapter.loadFromKeyId(pixKey.getId()).get();
        assertNotNull(account);
        assertEquals(validAccount.getId(), account.getId());
        assertEquals(validAccount.getAgency(), account.getAgency());
        assertEquals(validAccount.getNumber(), account.getNumber());
        assertEquals(validAccount.getType(), account.getType());
        assertEquals(validAccount.getHolder().getName(), account.getHolder().getName());
        assertEquals(validAccount.getHolder().getLastName(), account.getHolder().getLastName());
        assertEquals(validAccount.getHolder().getType(), account.getHolder().getType());
        assertEquals(validAccount.getPixKeys(), account.getPixKeys());
    }

}