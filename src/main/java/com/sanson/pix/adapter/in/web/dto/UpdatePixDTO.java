package com.sanson.pix.adapter.in.web.dto;

import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.HolderType;

import java.util.UUID;

public class UpdatePixDTO {

    private AccountType accountType;

    private Integer agencyNumber;

    private Integer accountNumber;

    private String holderName;

    private String holderLastName;

    private HolderType holderType;

    public UpdatePixDTO() {
    }

    public UpdatePixDTO(AccountType accountType, Integer agencyNumber,
                        Integer accountNumber, String holderName, String holderLastName, HolderType holderType) {
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.holderLastName = holderLastName;
        this.holderType = holderType;
    }

    public HolderType getHolderType() {
        return holderType;
    }

    public void setHolderType(HolderType holderType) {
        this.holderType = holderType;
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
}
