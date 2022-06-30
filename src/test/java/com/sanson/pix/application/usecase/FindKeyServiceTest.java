package com.sanson.pix.application.usecase;

import com.sanson.pix.application.port.in.FindKeyUseCase;
import com.sanson.pix.application.port.out.AccountPort;
import com.sanson.pix.domain.BusinessException;
import com.sanson.pix.domain.NotFoundException;
import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.Holder;
import com.sanson.pix.domain.managerPix.HolderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindKeyServiceTest {

    private FindKeyUseCase findKeyUseCase;

    @Mock
    private AccountPort accountPort;

    public Holder validHolderPF = new Holder("holderTest",null, HolderType.F);
    public Account validAccount = new Account(AccountType.CHECKING,
            1234,123456,validHolderPF, new ArrayList<>());

    @BeforeEach
    public void init() {
        findKeyUseCase = new FindKeyService(accountPort);
    }

    @Test
    public void shouldThrowExceptionIfKeyNotFoundForId() {

        var exception = assertThrows(NotFoundException.class,
                ()-> findKeyUseCase.findKey(UUID.randomUUID()));

        assertEquals("pix key not found", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionIfKeyNotFoundForAgencyAndNumber() {

        var exception = assertThrows(NotFoundException.class,
                ()-> findKeyUseCase.findKey(123,1235));

        assertEquals("pix key not found", exception.getMessage());
    }


    @Test
    public void shouldFindKeyById() {
        var id = UUID.randomUUID();
        when(accountPort.loadFromKeyId(eq(id))).thenReturn(Optional.of(validAccount));

        assertNotNull(findKeyUseCase.findKey(id));
    }

    @Test
    public void shouldFindKeyByAgencyAndNumber() {
        var agency= 123;
        var number=123;
        when(accountPort.findBy(eq(agency), eq(number))).thenReturn(Optional.of(validAccount));

        assertNotNull(findKeyUseCase.findKey(agency, number));
    }

    @Test
    public void shouldThrowExceptionIfAgencyIsNull() {

        var exception = assertThrows(BusinessException.class,
                ()-> findKeyUseCase.findKey(null,1235));

        assertEquals("account agency and number is required", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionIfAccountNumberIsNull() {

        var exception = assertThrows(BusinessException.class,
                ()-> findKeyUseCase.findKey(123,null));

        assertEquals("account agency and number is required", exception.getMessage());
    }

}