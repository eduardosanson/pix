package com.sanson.pix.application.port.in;

import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.Holder;
import com.sanson.pix.domain.managerPix.HolderType;

import java.util.Optional;
import java.util.UUID;

public class ChangeKeyCommand {

    private UUID id;

    private Optional<AccountType> accountType;

    private Optional<Integer> agencyNumber;

    private Optional<Integer> accountNumber;

    private Optional<HolderType> holderType;

    private Optional<String> holderName;

    private Optional<String> holderLastName;

    public ChangeKeyCommand(UUID id, Optional<AccountType> accountType, Optional<Integer> agencyNumber,
                            Optional<Integer> accountNumber, Optional<HolderType> holderType,
                            Optional<String> holderName, Optional<String> holderLastName) {
        this.id = id;
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.holderType = holderType;
        this.holderName = holderName;
        this.holderLastName = holderLastName;
    }

    public UUID getId(){
        return this.id;
    }

    public Account newFrom(Account other){

        var holder = new Holder(
                holderName.orElse(other.getHolder().getName()),
                holderLastName.orElse(other.getHolder().getLastName()),
                holderType.orElse(other.getHolder().getType())
        );

        return new Account(
                other.getId(),
                accountType.orElse(other.getType()),
                agencyNumber.orElse(other.getAgency()),
                accountNumber.orElse(other.getNumber()),
                holder, other.getPixKeys()
        );
    }
}
