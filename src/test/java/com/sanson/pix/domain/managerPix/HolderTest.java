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
        assertEquals("name is required", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenTryCreateHolderWithFirstNameNull(){
        var exception = assertThrows(BusinessException.class,()->{
            new Holder(null, null, HolderType.F);
        });
        assertEquals("name is required", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenTryCreateHolderWithHolderTypeNull(){
        var exception = assertThrows(BusinessException.class,()->{
            new Holder("holderName", null, null);
        });
        assertEquals("type is required", exception.getMessage());
    }

}