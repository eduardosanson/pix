package com.sanson.pix.application.port.in;

import com.sanson.pix.application.usecase.factory.PixKeyFactory;
import com.sanson.pix.domain.Error;
import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.Holder;
import com.sanson.pix.domain.managerPix.pixKeys.PixKey;
import com.sanson.pix.domain.managerPix.pixKeys.PixType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class SavePixKeyCommand {

    private static PixKeyFactory pixKeyFactory = new PixKeyFactory();

    private PixType pixType;

    private String pixValue;

    private AccountType accountType;

    private Integer agencyNumber;

    private Integer accountNumber;

    private Holder holder;

    public SavePixKeyCommand(PixType pixType, String pixValue, AccountType accountType, Integer agencyNumber,
                             Integer accountNumber, Holder holder) {
        this.pixType = pixType;
        this.pixValue = pixValue;
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.holder = holder;
        this.validate();
    }

    public void validate() {
        if (Objects.isNull(accountNumber) || Objects.isNull(agencyNumber)){
            Error.required("account number and agency number");
        }
    }

    public Account getAccount() {
        return new Account(accountType,agencyNumber, accountNumber,
                holder, new ArrayList<>(Arrays.asList(this.getPixKey())));
    }

    public Integer getAgencyNumber() {
        return agencyNumber;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public PixKey getPixKey() {
        return pixKeyFactory.createPixKey(pixType, pixValue);
    }
}
