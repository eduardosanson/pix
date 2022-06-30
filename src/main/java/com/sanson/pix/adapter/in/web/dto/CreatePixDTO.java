package com.sanson.pix.adapter.in.web.dto;

import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.HolderType;
import com.sanson.pix.domain.managerPix.pixKeys.PixType;

public class CreatePixDTO {

    private PixType pixType;

    private String pixValue;

    private AccountType accountType;

    private Integer agencyNumber;

    private Integer accountNumber;

    private String holderName;

    private String holderLastName;

    private HolderType HolderType;

    public CreatePixDTO() {
    }

    public CreatePixDTO(PixType pixType, String pixValue, AccountType accountType, Integer agencyNumber,
                        Integer accountNumber, String holderName, String holderLastName, HolderType holderType) {
        this.pixType = pixType;
        this.pixValue = pixValue;
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.holderLastName = holderLastName;
        HolderType = holderType;
    }

    public PixType getPixType() {
        return pixType;
    }

    public void setPixType(PixType pixType) {
        this.pixType = pixType;
    }

    public String getPixValue() {
        return pixValue;
    }

    public void setPixValue(String pixValue) {
        this.pixValue = pixValue;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Integer getAgencyNumber() {
        return agencyNumber;
    }

    public void setAgencyNumber(Integer agencyNumber) {
        this.agencyNumber = agencyNumber;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getHolderLastName() {
        return holderLastName;
    }

    public void setHolderLastName(String holderLastName) {
        this.holderLastName = holderLastName;
    }

    public com.sanson.pix.domain.managerPix.HolderType getHolderType() {
        return HolderType;
    }

    public void setHolderType(com.sanson.pix.domain.managerPix.HolderType holderType) {
        HolderType = holderType;
    }
}
