package com.sanson.pix.domain.managerPix;

import com.sanson.pix.domain.Error;
import com.sanson.pix.domain.managerPix.pixKeys.PixKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Account {

    private UUID id;

    private AccountType type;

    private Integer agency;

    private Integer number;

    private Holder holder;

    private List<PixKey> pixKeys;

    public Account(AccountType type, Integer agency, Integer number, Holder holder, List<PixKey> pixKeys) {
        if (agency == null || agency.toString().length()>4){
            Error.invalid("agency number");
        }
        if (number == null || number.toString().length()>8){
            Error.invalid("account number");
        }
        if (holder == null){
            Error.required("holder");
        }
        this.id = UUID.randomUUID();
        this.type = type;
        this.agency = agency;
        this.number = number;
        this.holder = holder;
        this.pixKeys = pixKeys;
        validatePixKeys(pixKeys);
    }

    public Account(UUID id, AccountType type, Integer agency, Integer number, Holder holder, List<PixKey> pixKeys) {
        this(type, agency, number, holder, pixKeys);
        this.id = id;
    }

    public void addNewPixKey(PixKey pixKey) {
        if (!pixKeys.contains(pixKey)){
            pixKeys.add(pixKey);
            validatePixKeys(this.pixKeys);
        }
    }

    private void validatePixKeys(List<PixKey> pixKeys) {
        if (HolderType.F == this.holder.getType() && pixKeys.size()>5){
            Error.notPermitted("add more keys");
        } else if(HolderType.J == this.holder.getType() && pixKeys.size()>20){
            Error.notPermitted("add more keys");
        }
    }


    public List<PixKey> getPixKeys() {
        return new ArrayList(this.pixKeys);
    }

    public Holder getHolder() {
        return holder;
    }

    public UUID getId() {
        return id;
    }

    public AccountType getType() {
        return type;
    }

    public Integer getAgency() {
        return agency;
    }

    public Integer getNumber() {
        return number;
    }

    public Optional<PixKey> getPixKeyById(UUID id){
        return this.pixKeys.stream()
                .filter((p)->p.getId().equals(id))
                .findFirst();
    }

    public Optional<PixKey> getPixKeyByValue(String value){
        return this.pixKeys.stream()
                .filter((p)->p.getValue().equals(value))
                .findFirst();
    }

}
