package com.sanson.pix.application.usecase;

import com.sanson.pix.application.port.in.FindKeyUseCase;
import com.sanson.pix.application.port.out.AccountPort;
import com.sanson.pix.domain.Error;
import com.sanson.pix.domain.NotFoundException;
import com.sanson.pix.domain.managerPix.Account;

import java.util.UUID;

import static java.util.Objects.isNull;

public class FindKeyService implements FindKeyUseCase {

    private AccountPort accountPort;

    public FindKeyService(AccountPort accountPort) {
        this.accountPort = accountPort;
    }

    public Account findKey(UUID id){

        return accountPort.loadFromKeyId(id)
                .orElseThrow(()-> new NotFoundException("pix key not found"));
    }

    public Account findKey(Integer agencyNumber, Integer accountNumber){

        if (isNull(agencyNumber) || isNull(accountNumber)){
            Error.required("account agency and number");
        }

        return accountPort.findBy(agencyNumber, accountNumber)
                .orElseThrow(()-> new NotFoundException("pix key not found"));
    }
}
