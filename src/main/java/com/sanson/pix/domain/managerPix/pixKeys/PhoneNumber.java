package com.sanson.pix.domain.managerPix.pixKeys;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

public class PhoneNumber extends PixKey{

    private static final String REGEX = "^\\++([0-9]{2}$)?([1-9]{4,5}$)?([0-9]{13,14}$)";

    public PhoneNumber(String value) {
        super(value, PixType.PHONE_NUMBER);
    }


    public PhoneNumber(UUID id, String value, LocalDateTime createdAt) {
        super(id, PixType.PHONE_NUMBER, value, createdAt);
    }

    @Override
    Boolean is_valid(String value) {
        Pattern pattern = Pattern.compile(REGEX);
        return pattern.matcher(value).matches();
    }
}
