package com.sanson.pix.application.usecase;

import com.sanson.pix.application.port.in.DisableKeyUseCase;
import com.sanson.pix.application.port.out.AccountPort;
import com.sanson.pix.application.port.out.UpdateKeyPort;
import com.sanson.pix.domain.NotFoundException;
import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.Holder;
import com.sanson.pix.domain.managerPix.HolderType;
import com.sanson.pix.domain.managerPix.pixKeys.Email;
import com.sanson.pix.domain.managerPix.pixKeys.PixKey;
import com.sanson.pix.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DisableKeyUseCaseTest {

    private DisableKeyUseCase disableKeyUseCase;

    @Mock
    private AccountPort accountPort;

    @Mock
    private UpdateKeyPort updateKeyPort;

    @Captor
    private ArgumentCaptor<PixKey> accountCaptor;

    @BeforeEach
    public void init(){
        disableKeyUseCase = new DisableKeyService(accountPort,updateKeyPort);
    }

    @Test
    public void shouldThrowNotFoundIfKeyNotFound() {

        var exception = assertThrows(NotFoundException.class,
                ()-> disableKeyUseCase.disableKey(UUID.randomUUID()));

        assertEquals("key not found", exception.getMessage());
    }

    @Test
    public void shouldDisableKey() {
        var pixKey = TestUtil.validEmail();
        var validAccount = TestUtil.validAccount(Arrays.asList(pixKey));

        when(accountPort.loadFromKeyId(eq(pixKey.getId())))
                .thenReturn(Optional.of(validAccount));

        disableKeyUseCase.disableKey(pixKey.getId());

        verify(updateKeyPort).updateKey(accountCaptor.capture());
        assertNotNull(accountCaptor.getValue().getDisabledAt());
    }

}