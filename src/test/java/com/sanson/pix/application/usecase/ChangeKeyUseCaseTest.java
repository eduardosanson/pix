package com.sanson.pix.application.usecase;

import com.sanson.pix.application.port.in.ChangeKeyCommand;
import com.sanson.pix.application.port.in.ChangeKeyUseCase;
import com.sanson.pix.application.port.out.AccountPort;
import com.sanson.pix.domain.NotFoundException;
import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.Holder;
import com.sanson.pix.domain.managerPix.HolderType;
import com.sanson.pix.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeKeyUseCaseTest {

    private ChangeKeyUseCase changeKeyUseCase;

    @Mock
    private AccountPort accountPort;


    @Captor
    private ArgumentCaptor<Account> accountCaptor;

    @BeforeEach
    public void init() {
        changeKeyUseCase = new ChangeKeyService(accountPort);
    }

    @Test
    public void shouldThrowNotFoundIfKeyNotFound() {
        var changeKeyCommand = new ChangeKeyCommand(UUID.randomUUID(), Optional.empty(),
                Optional.empty(),Optional.empty(), Optional.empty(),
                Optional.empty(),Optional.empty());
        var exception = assertThrows(NotFoundException.class,
                ()-> changeKeyUseCase.changeKey(changeKeyCommand));

        assertEquals("account not found", exception.getMessage());
    }

    @Test
    public void shouldSaveNewPixKey(){

        var changeKeyCommand = TestUtil.changeKeyCommand();
        var validAccount = TestUtil.validAccount();

        when(accountPort.load(eq(changeKeyCommand.getId())))
                .thenReturn(Optional.of(validAccount));

        changeKeyUseCase.changeKey(changeKeyCommand);

        verify(accountPort).save(accountCaptor.capture());
        assertEquals(validAccount.getId(), accountCaptor.getValue().getId());
    }

}