package com.sanson.pix.application.usecase.factory;

import com.sanson.pix.domain.BusinessException;
import com.sanson.pix.domain.Error;
import com.sanson.pix.domain.managerPix.pixKeys.CPF;
import com.sanson.pix.domain.managerPix.pixKeys.Email;
import com.sanson.pix.domain.managerPix.pixKeys.PhoneNumber;
import com.sanson.pix.domain.managerPix.pixKeys.PixKey;
import com.sanson.pix.domain.managerPix.pixKeys.PixType;

import java.time.LocalDateTime;
import java.util.UUID;

public class PixKeyFactory {

    public PixKey createPixKey(PixType pixType, String pixValue){

        switch(pixType){
            case CPF:
                return new CPF(pixValue);
            case EMAIL:
                return new Email(pixValue);
            case PHONE_NUMBER:
                return new PhoneNumber(pixValue);
            default:
                throw new BusinessException("pix type is invalid");
        }
    }

    public PixKey createPixKey(UUID id, PixType pixType, String value, LocalDateTime createdAt){

        switch(pixType){
            case CPF:
                return new CPF(id, value, createdAt);
            case EMAIL:
                return new Email(id, value, createdAt);
            case PHONE_NUMBER:
                return new PhoneNumber(id, value, createdAt);
            default:
                throw new BusinessException("pix type is invalid");
        }
    }
}
