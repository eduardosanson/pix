package com.sanson.pix.domain.managerPix.pixKeys;

import com.sanson.pix.domain.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "teste.com", "",
            "dlçakdlçaksçdlkaslçdkasçsldkasçldkasçldkaslçkdçalskdlçaskdleereçaskç@teste.com"
    })
    public void shouldThrowExceptionWhenEmailIsInvalid(String cpf) {
        BusinessException exception = assertThrows(BusinessException.class, () -> new Email(cpf));
        assertEquals("email is invalid", exception.getMessage());
    }

    @Test
    public void shouldCreateEmailKey() {
        assertNotNull(new Email("teste@teste.com"));
    }

    @Test
    public void shouldThrowExceptionWhenEmailKeyIsNull() {
        BusinessException exception = assertThrows(BusinessException.class, () -> new Email(null));
        assertEquals("email is invalid", exception.getMessage());
    }

}