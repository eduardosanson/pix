package com.sanson.pix.adapter.in.web.dto;

import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.HolderType;
import com.sanson.pix.domain.managerPix.pixKeys.PixType;

import java.time.LocalDateTime;
import java.util.UUID;

public class DeletePixResponseDTO {

    private String id;

    private PixType pixType;

    private String pixValue;

    private AccountType accountType;

    private Integer agencyNumber;

    private Integer accountNumber;

    private String holderName;

    private String holderLastName;

    private HolderType holderType;

    private LocalDateTime createdAt;

    private LocalDateTime disabledAt;

    public DeletePixResponseDTO(String id, PixType pixType, String pixValue, AccountType accountType,
                                Integer agencyNumber, Integer accountNumber, String holderName, String holderLastName,
                                HolderType holderType, LocalDateTime createdAt, LocalDateTime disabledAt) {
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
        this.disabledAt = disabledAt;
    }

    public LocalDateTime getDisabledAt() {
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
        return holderLastName;
    }

    public HolderType getHolderType() {
        return holderType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
