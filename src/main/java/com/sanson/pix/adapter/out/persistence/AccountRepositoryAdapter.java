package com.sanson.pix.adapter.out.persistence;

import com.sanson.pix.adapter.out.persistence.entity.AccountEntity;
import com.sanson.pix.adapter.out.persistence.entity.HolderEntity;
import com.sanson.pix.adapter.out.persistence.entity.PixKeyEntity;
import com.sanson.pix.application.port.out.AccountPort;
import com.sanson.pix.application.usecase.factory.PixKeyFactory;
import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.Holder;
import com.sanson.pix.domain.managerPix.pixKeys.PixKey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountRepositoryAdapter implements AccountPort {

    private final AccountRepository accountRepository;

    private static PixKeyFactory pixKeyFactory = new PixKeyFactory();

    public AccountRepositoryAdapter(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> load(UUID id) {
        return accountRepository.findById(id).map(this::accountEntityToDomain);
    }

    @Override
    public Optional<Account> loadFromKeyId(UUID id) {
        return accountRepository.findByPixKeyEntitiesId(id).map(this::accountEntityToDomain);
    }

    @Override
    public Optional<Account> findBy(Integer agency, Integer number) {
        return accountRepository.findByAgencyAndNumber(agency,number).map(this::accountEntityToDomain);
    }

    @Override
    public Account save(Account account) {
        HolderEntity holder = new HolderEntity(account.getHolder().getId(), account.getHolder().getName(),
                account.getHolder().getLastName(), account.getHolder().getType());

        List<PixKeyEntity> pixKeyEntities = new ArrayList<>();
        account.getPixKeys().forEach((pixKey)->{
            pixKeyEntities.add(new PixKeyEntity(pixKey.getId(),pixKey.getValue(), pixKey.getType(),
                    pixKey.getCreatedAt(), pixKey.getDisabledAt()));
        });

        AccountEntity accountEntity = accountRepository.save(
                new AccountEntity(account.getId(),holder, account.getNumber(),
                        account.getAgency(),account.getType(),pixKeyEntities));
        return accountEntityToDomain(accountEntity);
    }

    private Account accountEntityToDomain(AccountEntity ac) {
        var holder = new Holder(
                ac.getHolder().getId(),
                ac.getHolder().getName(),
                ac.getHolder().getLastName(),
                ac.getHolder().getType());
        List<PixKey> pixKeys = new ArrayList<>();
        ac.getPixKeys().forEach(pixKey ->{
            pixKeys.add(pixKeyFactory.createPixKey(pixKey.getId(),pixKey.getType(),
                    pixKey.getValue(), pixKey.getCreatedAt()));
        });
        var account = new Account(ac.getId(), ac.getType(), ac.getAgency(), ac.getNumber(), holder, pixKeys);
        return account;
    }
}
