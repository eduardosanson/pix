package com.sanson.pix.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;
import com.sanson.pix.application.port.in.ChangeKeyCommand;
import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.Holder;
import com.sanson.pix.domain.managerPix.HolderType;
import com.sanson.pix.domain.managerPix.pixKeys.Email;
import com.sanson.pix.domain.managerPix.pixKeys.PixKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TestUtil {

    private static Faker faker = new Faker();

    public static Account validAccount(){
        var validHolderPF = new Holder(faker.name().name(),null, HolderType.F);
        return new Account(AccountType.CHECKING, Integer.parseInt(faker.number().digits(4)),
                Integer.parseInt(faker.number().digits(4)),validHolderPF, new ArrayList<>());
    }

    public static Account validAccount(List<PixKey> pixKeys){
        var validHolderPF = new Holder(faker.name().name(),null, HolderType.F);
        return new Account(AccountType.CHECKING, Integer.parseInt(faker.number().digits(4)),
                Integer.parseInt(faker.number().digits(4)),validHolderPF, new ArrayList<>(pixKeys));
    }

    public static Email validEmail(){
        return new Email(faker.internet().emailAddress());
    }

    public static String convertToJson(Object request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(request);
    }

    public static ChangeKeyCommand changeKeyCommand (){
        return new ChangeKeyCommand(UUID.randomUUID(),
                Optional.of(AccountType.SAVINGS), Optional.of(Integer.parseInt(faker.number().digits(4))),
                Optional.of(Integer.parseInt(faker.number().digits(8))),
                Optional.of(HolderType.J), Optional.of(faker.name().name()), Optional.of(faker.name().lastName())) ;
    }


}
