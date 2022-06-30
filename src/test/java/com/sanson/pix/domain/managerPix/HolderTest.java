package com.sanson.pix.domain.managerPix;

import com.sanson.pix.domain.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HolderTest {

    @Test
    public void shouldThrowExceptionWhenTryCreateHolderWithFirstNameEmpty(){
        var exception = assertThrows(BusinessException.class,()->{
            new Holder("", null, HolderType.F);
        });
        assertEquals("holder name is invalid", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenTryCreateHolderWithFirstNameNull(){
        var exception = assertThrows(BusinessException.class,()->{
            new Holder(null, null, HolderType.F);
        });
        assertEquals("holder name is invalid", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenTryCreateHolderWithHolderTypeNull(){
        var exception = assertThrows(BusinessException.class,()->{
            new Holder("holderName", null, null);
        });
        assertEquals("holder type is invalid", exception.getMessage());
    }

}