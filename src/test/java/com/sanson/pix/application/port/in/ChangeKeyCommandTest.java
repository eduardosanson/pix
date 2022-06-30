package com.sanson.pix.application.port.in;

import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.Holder;
import com.sanson.pix.domain.managerPix.HolderType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChangeKeyCommandTest {

    @Test
    public void shouldCreateNewAccount(){
        var validChangeKeyCommand = new ChangeKeyCommand(UUID.randomUUID(),
                Optional.of(AccountType.SAVINGS), Optional.of(1234), Optional.of(1245),
                Optional.of(HolderType.J), Optional.of("holderName"), Optional.of("")) ;
        var holder = new Holder("teste", null, HolderType.J);
        var account = new Account(AccountType.SAVINGS, 1234, 12345, holder, new ArrayList<>());

        var newAccount = validChangeKeyCommand.newFrom(account);

        assertNotNull(newAccount);
        assertEquals(account.getId(), newAccount.getId());

    }

}