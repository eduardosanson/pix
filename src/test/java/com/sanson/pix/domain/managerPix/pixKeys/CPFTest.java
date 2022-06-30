package com.sanson.pix.domain.managerPix.pixKeys;

import com.sanson.pix.domain.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CPFTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "759.638.120-03", "123", "7596381203",""
    })
    public void shouldThrowExceptionWhenCPFIsInvalid(String cpf) {
        BusinessException exception = assertThrows(BusinessException.class, () -> new CPF(cpf));
        assertEquals("cpf is invalid", exception.getMessage());
    }

    @Test
    public void shouldCreateCPFKey() {
        assertNotNull(new CPF("75963812003"));
    }

    @Test
    public void shouldThrowExceptionWhenCPFKeyIsNull() {
        BusinessException exception = assertThrows(BusinessException.class, () -> new CPF(null));
        assertEquals("cpf is invalid", exception.getMessage());
    }

}