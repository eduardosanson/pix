package com.sanson.pix.domain.managerPix.pixKeys;

import com.sanson.pix.domain.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneNumberTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "","+","+55", "21987721599", "987721599","5521987721599","+552198772159", "+55219877215912"
    })
    public void shouldThrowExceptionWhenPhoneKeyIsInvalid(String phoneNumber) {

        BusinessException exception = assertThrows(BusinessException.class, () -> new PhoneNumber(phoneNumber));

        assertEquals("phone number is invalid", exception.getMessage());

    }

    @Test
    public void shouldCreatePhoneNumberKey() {
        assertNotNull(new PhoneNumber("+5521987721591"));
    }

    @Test
    public void shouldThrowExceptionWhenPhoneKeyIsNull() {

        BusinessException exception = assertThrows(BusinessException.class, () -> new PhoneNumber(null));

        assertEquals("phone number is invalid", exception.getMessage());

    }

}