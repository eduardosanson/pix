package com.sanson.pix.application.port.in;

import com.sanson.pix.domain.BusinessException;
import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.Holder;
import com.sanson.pix.domain.managerPix.HolderType;
import com.sanson.pix.domain.managerPix.pixKeys.CPF;
import com.sanson.pix.domain.managerPix.pixKeys.Email;
import com.sanson.pix.domain.managerPix.pixKeys.PhoneNumber;
import com.sanson.pix.domain.managerPix.pixKeys.PixType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavePixKeyCommandTest {

    public Holder validHolderPF = new Holder("holderTest",null, HolderType.F);

    @Test
    public void shouldCreateAValidPixKeyCPF() {
        var savePixKeyCommand = new SavePixKeyCommand(PixType.CPF, "30397682042",
                AccountType.CHECKING, 1234,12345, validHolderPF);
        assertInstanceOf(CPF.class, savePixKeyCommand.getPixKey()) ;
    }

    @Test
    public void shouldCreateAValidPixKeyEmail() {
        var savePixKeyCommand = new SavePixKeyCommand(PixType.EMAIL, "teste@teste.com",
                AccountType.CHECKING, 1234,12345, validHolderPF);
        assertInstanceOf(Email.class, savePixKeyCommand.getPixKey()) ;
    }

    @Test
    public void shouldCreateAValidPixKeyPhoneNumber() {
        var savePixKeyCommand = new SavePixKeyCommand(PixType.PHONE_NUMBER, "+5521981798171",
                AccountType.CHECKING, 1234,12345, validHolderPF);
        assertInstanceOf(PhoneNumber.class, savePixKeyCommand.getPixKey()) ;
    }

    @Test
    public void shouldCreateAValidAccount() {
        var savePixKeyCommand = new SavePixKeyCommand(PixType.PHONE_NUMBER, "+5521981798171",
                AccountType.CHECKING, 1234,12345, validHolderPF);
        savePixKeyCommand.getAccount();
        assertNotNull(savePixKeyCommand.getAccount());
    }

    @Test
    public void shouldThrowExceptionWhenAccountNumberIsNull() {
        BusinessException exception = assertThrows(BusinessException.class,
                () -> new SavePixKeyCommand(PixType.PHONE_NUMBER, "+5521981798171",
                AccountType.CHECKING, 1234,null, validHolderPF));
        assertEquals("account number and agency number is required", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenAgencyNumberIsNull() {
        BusinessException exception = assertThrows(BusinessException.class,
                () -> new SavePixKeyCommand(PixType.PHONE_NUMBER, "+5521981798171",
                        AccountType.CHECKING, null,1234, validHolderPF));
        assertEquals("account number and agency number is required", exception.getMessage());
    }

}