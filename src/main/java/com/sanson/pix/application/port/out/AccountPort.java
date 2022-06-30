package com.sanson.pix.application.port.out;

import com.sanson.pix.domain.managerPix.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountPort {

    Optional<Account> load(UUID id);
    Optional<Account> loadFromKeyId(UUID id);
    Optional<Account> findBy(Integer agency, Integer number);

    Account save(Account account);
}
