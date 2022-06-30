package com.sanson.pix.application.port.in;

import com.sanson.pix.domain.managerPix.Account;

import java.util.UUID;

public interface DisableKeyUseCase {

    Account disableKey(UUID id);
}
