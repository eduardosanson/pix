package com.sanson.pix.adapter.in.web.dto;

import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.HolderType;
import com.sanson.pix.domain.managerPix.pixKeys.PixType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class PixResponseDTO {

    private String id;

    private PixType pixType;

    private String pixValue;

    private AccountType accountType;

    private Integer agencyNumber;

    private Integer accountNumber;

    private String holderName;

    private String holderLastName;

    private HolderType holderType;

    private LocalDate createdAt;

    private String disabledAt;

    public PixResponseDTO(String id, PixType pixType, String pixValue, AccountType accountType, Integer agencyNumber,
                          Integer accountNumber, String holderName, String holderLastName, HolderType holderType,
                          LocalDate createdAt, LocalDate disabledAt) {
        this.id = id;
        this.pixType = pixType;
        this.pixValue = pixValue;
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.holderLastName = holderLastName;
        this.holderType = holderType;
        this.createdAt = createdAt;
        this.disabledAt = disabledAt != null? disabledAt.toString() : "";
    }

    public String getDisabledAt() {
        return disabledAt;
    }

    public UUID getId() {
        return UUID.fromString(id);
    }


    public PixType getPixType() {
        return pixType;
    }


    public String getPixValue() {
        return pixValue;
    }


    public AccountType getAccountType() {
        return accountType;
    }

    public Integer getAgencyNumber() {
        return agencyNumber;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getHolderLastName() {
        if(holderLastName == null){
            return "";
        }
        return holderLastName;
    }

    public HolderType getHolderType() {
        return holderType;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
