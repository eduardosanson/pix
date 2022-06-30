package com.sanson.pix.application.usecase;

import com.sanson.pix.application.port.in.SavePixKeyCommand;
import com.sanson.pix.application.port.out.AccountPort;
import com.sanson.pix.application.port.in.SavePixKeyUseCase;
import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.Holder;
import com.sanson.pix.domain.managerPix.HolderType;
import com.sanson.pix.domain.managerPix.pixKeys.PhoneNumber;
import com.sanson.pix.domain.managerPix.pixKeys.PixType;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SavePixKeyUseCaseTest {

    private SavePixKeyUseCase savePixKeyUseCase;

    @Mock
    private AccountPort accountPort;

    @Captor
    private ArgumentCaptor<Account> accountCaptor;

    public Account validAccount = TestUtil.validAccount();

    @BeforeEach
    public void init(){
        this.savePixKeyUseCase = new SavePixKeyService(accountPort);
    }

    @Test
    public void shouldSaveNewPixKeyWhenAccountNotExistsYet(){

        var holder = validAccount.getHolder();

        var savePixKeyCommand = new SavePixKeyCommand(PixType.PHONE_NUMBER, "+5521981798171",
                AccountType.CHECKING, 1234,12345, holder);

        when(accountPort.findBy(anyInt(), anyInt())).thenReturn(Optional.empty());

        validAccount.addNewPixKey(new PhoneNumber("+5521981798171"));

        when(accountPort.save(any())).thenReturn(validAccount);

        var pixKey = savePixKeyUseCase.saveKey(savePixKeyCommand);

        assertNotNull(pixKey);

        verify(accountPort).findBy(eq(savePixKeyCommand.getAgencyNumber()),
                eq(savePixKeyCommand.getAccountNumber()));
        verify(accountPort).save(accountCaptor.capture());
        assertEquals(pixKey, accountCaptor.getValue().getPixKeys().get(0));

    }

    @Test
    public void shouldSaveNewPixKeyWhenAccountAlreadyExists(){
        var holder = validAccount.getHolder();

        var savePixKeyCommand = new SavePixKeyCommand(PixType.PHONE_NUMBER, "+5521981798171",
                AccountType.CHECKING, 1234,12345, holder);

        when(accountPort.findBy(anyInt(), anyInt()))
                .thenReturn(Optional.of(validAccount));

        validAccount.addNewPixKey(new PhoneNumber("+5521981798171"));

        when(accountPort.save(any())).thenReturn(validAccount);

        var pixKey = savePixKeyUseCase.saveKey(savePixKeyCommand);

        assertNotNull(pixKey);

        verify(accountPort).findBy(eq(savePixKeyCommand.getAgencyNumber()),
                eq(savePixKeyCommand.getAccountNumber()));
        verify(accountPort).save(accountCaptor.capture());
        assertEquals(pixKey, accountCaptor.getValue().getPixKeys().get(0));
    }

}