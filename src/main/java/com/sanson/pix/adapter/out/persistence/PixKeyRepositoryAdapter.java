package com.sanson.pix.adapter.out.persistence;

import com.sanson.pix.adapter.out.persistence.entity.PixKeyEntity;
import com.sanson.pix.application.port.out.UpdateKeyPort;
import com.sanson.pix.application.usecase.factory.PixKeyFactory;
import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.pixKeys.PixKey;
import org.springframework.stereotype.Service;

@Service
public class PixKeyRepositoryAdapter implements UpdateKeyPort {

    private PixKeyRepository pixKeyRepository;

    private static PixKeyFactory pixKeyFactory =  new PixKeyFactory();

    public PixKeyRepositoryAdapter(PixKeyRepository pixKeyRepository) {
        this.pixKeyRepository = pixKeyRepository;
    }

    @Override
    public PixKey updateKey(PixKey pixKey) {
        var pixKeyEntity = pixKeyRepository.save(pixKeyDomainToEntity(pixKey));
        return pixKeyFactory.createPixKey(pixKeyEntity.getId(), pixKeyEntity.getType(),
                pixKeyEntity.getValue(), pixKeyEntity.getCreatedAt());
    }

    private PixKeyEntity pixKeyDomainToEntity(PixKey pixKey) {
        return new PixKeyEntity(pixKey.getId(), pixKey.getValue(), pixKey.getType(),
                pixKey.getCreatedAt(), pixKey.getDisabledAt());
    }
}
