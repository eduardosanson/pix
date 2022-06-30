package com.sanson.pix.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.Holder;
import com.sanson.pix.domain.managerPix.HolderType;
import com.sanson.pix.domain.managerPix.pixKeys.Email;

import java.util.ArrayList;

public class TestUtil {

    public static Account validAccount(){
        var validHolderPF = new Holder("holderTest",null, HolderType.F);
        return new Account(AccountType.CHECKING,
                1234,123456,validHolderPF, new ArrayList<>());
    }

    public static Email validEmail(){
        return new Email("teste@teste.com");
    }

    public static String convertToJson(Object request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(request);
    }


}
