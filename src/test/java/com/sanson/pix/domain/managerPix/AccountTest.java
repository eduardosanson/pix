package com.sanson.pix.domain.managerPix;

import com.sanson.pix.domain.BusinessException;
import com.sanson.pix.domain.managerPix.pixKeys.CPF;
import com.sanson.pix.domain.managerPix.pixKeys.Email;
import com.sanson.pix.domain.managerPix.pixKeys.PhoneNumber;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTest {

    @Test
    public void shouldThrowExceptionWhenTryCreateAccountWithoutHolder() {
        BusinessException exception = assertThrows(BusinessException.class, () -> new Account(AccountType.CHECKING,1234,123456,null, Arrays.asList()));
        assertEquals("holder is required", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenTryCreateAccountWithInvalidAccountNumber() {
        BusinessException exception = assertThrows(BusinessException.class,
                () -> new Account(AccountType.CHECKING,1234,123456789,null, Arrays.asList()));
        assertEquals("account number is invalid", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenTryCreateAccountWithInvalidAgencyNumber() {
        BusinessException exception = assertThrows(BusinessException.class,
                () -> new Account(AccountType.CHECKING,12345,123456,null, Arrays.asList()));
        assertEquals("agency number is invalid", exception.getMessage());
    }

    @Test
    public void shouldCreateAccount() {
        var holder = new Holder("holder", null, HolderType.F);
        assertNotNull(new Account(AccountType.CHECKING,1234,123456,holder, Arrays.asList()));
    }

    @Test
    public void shouldAddPixKeyWhenPixDoesNotExist() {
        var holder = new Holder("holder", null, HolderType.F);
        var account = new Account(AccountType.CHECKING,1234,123456,holder, new ArrayList());
        var pixKey = new Email("teste@teste.com");
        account.addNewPixKey(pixKey);

        assertEquals(pixKey, account.getPixKeys().get(0));
        assertTrue(account.getPixKeys().contains(pixKey));
    }

    @Test
    public void shoulNotdAddPixKeyWhenPixAlreadyExist() {
        var holder = new Holder("holder", null, HolderType.F);
        var account = new Account(AccountType.CHECKING,1234,123456,holder, new ArrayList());
        var pixKey = new Email("teste@teste.com");
        account.addNewPixKey(pixKey);

        account.addNewPixKey(new Email("teste@teste.com"));
        assertEquals(pixKey, account.getPixKeys().get(0));
        assertTrue(account.getPixKeys().contains(pixKey));
        assertTrue(account.getPixKeys().size()==1);

    }

    @Test
    public void shouldNotdAddPixKeyWhenLimitExceededForPF() {
        var holder = new Holder("holder", null, HolderType.F);
        var pixKeys = new ArrayList<>(Arrays.asList(new Email("teste@teste.com)"),
                new PhoneNumber("+5521981234521"), new CPF("76782926008"), new CPF("86545763083"),
                new Email("teste2@teste.com)")));
        var account = new Account(AccountType.CHECKING,1234,123456,holder, pixKeys);

        BusinessException exception = assertThrows(BusinessException.class,
                () -> account.addNewPixKey(new Email("teste3@teste.com")));
        assertEquals("is not permitted add more keys", exception.getMessage());
    }

    @Test
    public void shouldNotAddPixKeyWhenLimitExceededForPJ() {
        var holder = new Holder("holder", null, HolderType.J);
        var pixKeys =new ArrayList<>(Arrays.asList(new Email("teste@teste.com)"),
                new PhoneNumber("+5521981234521"), new CPF("76782926008"), new CPF("86545763083"),
                new Email("teste2@teste.com)"), new Email("teste4@teste.com)"),
                new Email("teste5@teste.com)"), new Email("teste6@teste.com)"),
                new Email("teste7@teste.com)"), new Email("teste8@teste.com)"),
                new Email("teste9@teste.com)"),new CPF("87569086015"), new CPF("59368601089"),
                new CPF("75423252059"), new CPF("86630913035"), new CPF("59368601089"),
                new CPF("61517179025"), new CPF("84351761008"), new CPF("59931400030"),
                new PhoneNumber("+5521981254521")));

        var account = new Account(AccountType.CHECKING,1234,123456,holder, pixKeys);

        BusinessException exception = assertThrows(BusinessException.class,
                () -> account.addNewPixKey(new Email("teste3@teste.com")));
        assertEquals("is not permitted add more keys", exception.getMessage());
    }

    @Test
    public void shoulNotdCreateAccoubtWhenPixKeysLimitExceededForPJ() {
        var holder = new Holder("holder", null, HolderType.J);
        var pixKeys =new ArrayList<>(Arrays.asList(new Email("teste@teste.com)"),
                new PhoneNumber("+5521981234521"), new CPF("76782926008"), new CPF("86545763083"),
                new Email("teste2@teste.com)"), new Email("teste4@teste.com)"),
                new Email("teste5@teste.com)"), new Email("teste6@teste.com)"),
                new Email("teste7@teste.com)"), new Email("teste8@teste.com)"),
                new Email("teste9@teste.com)"),new CPF("87569086015"), new CPF("59368601089"),
                new CPF("75423252059"), new CPF("86630913035"), new CPF("59368601089"),
                new CPF("61517179025"), new CPF("84351761008"), new CPF("59931400030"),
                new PhoneNumber("+5521981254521"), new PhoneNumber("+5521981234523"),
                new Email("teste3@teste.com")));

        BusinessException exception = assertThrows(BusinessException.class,
                () -> new Account(AccountType.CHECKING,1234,123456,holder, pixKeys));
        assertEquals("is not permitted add more keys", exception.getMessage());
    }

    @Test
    public void shouldNotCreateAccountWhenPixKeysLimitExceededForPF() {
        var holder = new Holder("holder", null, HolderType.F);
        var pixKeys = Arrays.asList(new Email("teste@teste.com)"),
                new PhoneNumber("+5521981234521"), new CPF("76782926008"), new CPF("86545763083"),
                new Email("teste2@teste.com)"), new Email("teste3@teste.com"));

        BusinessException exception = assertThrows(BusinessException.class,
                () -> new Account(AccountType.CHECKING,1234,123456,holder, pixKeys));
        assertEquals("is not permitted add more keys", exception.getMessage());
    }

}