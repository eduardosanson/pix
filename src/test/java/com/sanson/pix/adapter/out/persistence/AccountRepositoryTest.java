package com.sanson.pix.adapter.out.persistence;

import com.sanson.pix.adapter.out.persistence.entity.AccountEntity;
import com.sanson.pix.adapter.out.persistence.entity.HolderEntity;
import com.sanson.pix.adapter.out.persistence.entity.PixKeyEntity;
import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.HolderType;
import com.sanson.pix.domain.managerPix.pixKeys.PixType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void saveNewAccount() {
        var holder = new HolderEntity("holderName", null, HolderType.F);
        var pixKey = new PixKeyEntity("teste@teste.com", PixType.EMAIL);
        AccountEntity accountEntity = new AccountEntity(holder, 1234, 12345,
                AccountType.CHECKING, Arrays.asList(pixKey));

        accountRepository.save(accountEntity);
    }
}