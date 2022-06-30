package com.sanson.pix.adapter.out.persistence;

import com.sanson.pix.domain.managerPix.pixKeys.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PixKeyRepositoryAdapterTest {

    private PixKeyRepositoryAdapter  pixKeyRepositoryAdapter;

    @Autowired
    private PixKeyRepository pixKeyRepository;

    @BeforeEach
    public void init() {
        pixKeyRepositoryAdapter = new PixKeyRepositoryAdapter(pixKeyRepository);
    }

    @Test
    public void test(){
        var validPixKey = new Email("teste@teste.com");
        var pixKey = pixKeyRepositoryAdapter.updateKey(validPixKey);

        assertEquals(validPixKey.getId(), pixKey.getId());
        assertEquals(validPixKey.getValue(), pixKey.getValue());
        assertEquals(validPixKey.getCreatedAt(), pixKey.getCreatedAt());
        assertEquals(validPixKey.getDisabledAt(), pixKey.getDisabledAt());
        assertEquals(validPixKey.getType(), pixKey.getType());
    }



}