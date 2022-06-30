package com.sanson.pix.domain.managerPix.pixKeys;

public enum PixType {
    CPF("cpf"), EMAIL("email"),PHONE_NUMBER("phone number");

    private String value;

    PixType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
