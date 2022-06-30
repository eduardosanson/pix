package com.sanson.pix.application.usecase.factory;

import com.sanson.pix.domain.managerPix.pixKeys.CPF;
import com.sanson.pix.domain.managerPix.pixKeys.Email;
import com.sanson.pix.domain.managerPix.pixKeys.PhoneNumber;
import com.sanson.pix.domain.managerPix.pixKeys.PixType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PixKeyFactoryTest {

    private PixKeyFactory pixKeyFactory = new PixKeyFactory();

    @Test
    public void shouldCreateEmailPixKey() {
        var key = pixKeyFactory.createPixKey(UUID.randomUUID(), PixType.EMAIL,
                "teste@teste.com",LocalDateTime.now());
        assertInstanceOf(Email.class, key);
    }

    @Test
    public void shouldCreateCPFPixKey() {
        var key = pixKeyFactory.createPixKey(UUID.randomUUID(), PixType.CPF,
                "74108400216",LocalDateTime.now());
        assertInstanceOf(CPF.class, key);
    }
    @Test
    public void shouldCreatePhoneNumberPixKey() {
        var key = pixKeyFactory.createPixKey(UUID.randomUUID(), PixType.PHONE_NUMBER,
                "+5521903272087",LocalDateTime.now());
        assertInstanceOf(PhoneNumber.class, key);
    }

}