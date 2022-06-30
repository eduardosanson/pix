package com.sanson.pix.application.port.in;

import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.pixKeys.PixType;

import java.util.List;
import java.util.UUID;

public interface FindKeyUseCase {

    Account findKey(UUID id);

    Account findKey(Integer agencyNumber, Integer accountNumber);

    List<Account> findKeyType(PixType type);
}
