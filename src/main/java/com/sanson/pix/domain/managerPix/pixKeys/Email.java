package com.sanson.pix.domain.managerPix.pixKeys;

import java.time.LocalDateTime;
import java.util.UUID;

public class Email extends PixKey {

    public Email(String value) {
        super(value, PixType.EMAIL);
    }

    public Email(UUID id, String value){
        super(value, PixType.EMAIL, id);
    }

    public Email(UUID id, String value, LocalDateTime createdAt) {
        super(id,PixType.EMAIL, value, createdAt);
    }

    @Override
    Boolean is_valid(String value) {
        return value.contains("@") && value.length()<=77;
    }


}
