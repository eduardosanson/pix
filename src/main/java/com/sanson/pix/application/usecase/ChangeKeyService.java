package com.sanson.pix.application.usecase;

import com.sanson.pix.application.port.in.ChangeKeyCommand;
import com.sanson.pix.application.port.in.ChangeKeyUseCase;
import com.sanson.pix.application.port.out.AccountPort;
import com.sanson.pix.domain.NotFoundException;
import com.sanson.pix.domain.managerPix.Account;

public class ChangeKeyService implements ChangeKeyUseCase {

    private AccountPort accountPort;


    public ChangeKeyService(AccountPort accountPort) {
        this.accountPort = accountPort;
    }

    public Account changeKey(ChangeKeyCommand changeKeyCommand){

        var account = accountPort.load(changeKeyCommand.getId())
                .orElseThrow(()-> new NotFoundException("account not found"));

        return accountPort.save(changeKeyCommand.newFrom(account));

    }
}
