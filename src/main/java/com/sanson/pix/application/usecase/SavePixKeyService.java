package com.sanson.pix.application.usecase;

import com.sanson.pix.application.port.in.SavePixKeyCommand;
import com.sanson.pix.application.port.in.SavePixKeyUseCase;
import com.sanson.pix.application.port.out.AccountPort;
import com.sanson.pix.domain.Error;
import com.sanson.pix.domain.managerPix.pixKeys.PixKey;

import java.util.Optional;

public class SavePixKeyService implements SavePixKeyUseCase {

    private AccountPort accountPort;

    public SavePixKeyService(AccountPort accountPort) {
        this.accountPort = accountPort;
    }

    public PixKey saveKey(SavePixKeyCommand savePixKeyCommand){        
        var account = accountPort.findBy(savePixKeyCommand.getAgencyNumber(),
                savePixKeyCommand.getAccountNumber());

        Optional<PixKey> pixKey = null;
        if (account.isEmpty()){
            pixKey = accountPort.save(savePixKeyCommand.getAccount())
                    .getPixKeyByValue(savePixKeyCommand.getPixKey().getValue()); 
        }else {
            account.get().addNewPixKey(savePixKeyCommand.getPixKey());
            pixKey = accountPort.save(account.get())
                    .getPixKeyByValue(savePixKeyCommand.getPixKey().getValue());
        }

        return pixKey.get();
    }
}
