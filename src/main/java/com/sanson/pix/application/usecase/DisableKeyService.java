package com.sanson.pix.application.usecase;

import com.sanson.pix.application.port.in.DisableKeyUseCase;
import com.sanson.pix.application.port.out.AccountPort;
import com.sanson.pix.application.port.out.UpdateKeyPort;
import com.sanson.pix.domain.NotFoundException;
import com.sanson.pix.domain.managerPix.Account;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
public class DisableKeyService implements DisableKeyUseCase {

    private AccountPort accountPort;

    private UpdateKeyPort updateKeyPort;

    public DisableKeyService(AccountPort accountPort, UpdateKeyPort updateKeyPort) {
        this.accountPort = accountPort;
        this.updateKeyPort = updateKeyPort;
    }

    public Account disableKey(UUID id){
        var account = accountPort.loadFromKeyId(id)
                .orElseThrow(()-> new NotFoundException("key not found"));

        var pixKey = account.getPixKeys().stream()
                .filter((p)-> p.getId().equals(id))
                .findFirst().get();
        pixKey.disable();

        updateKeyPort.updateKey(pixKey);

        return new Account(account.getId(), account.getType(), account.getAgency(), account.getNumber(),
                account.getHolder(), Arrays.asList(pixKey));


    }
}
