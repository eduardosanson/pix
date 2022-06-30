package com.sanson.pix.adapter.out.persistence;

import com.sanson.pix.adapter.out.persistence.entity.PixKeyEntity;
import com.sanson.pix.domain.managerPix.pixKeys.PixType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PixKeyRepositoryTest {

    @Autowired
    private PixKeyRepository pixKeyRepository;

    @Test
    public void savePixKey(){
        var pixKey = new PixKeyEntity("teste@teste.com", PixType.EMAIL);
        pixKeyRepository.save(pixKey);
        assertFalse(pixKeyRepository.findById(pixKey.getId()).isEmpty());
    }

}