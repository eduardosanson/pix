package com.sanson.pix.application.port.in;

import com.sanson.pix.domain.managerPix.Account;

public interface ChangeKeyUseCase {

    Account changeKey(ChangeKeyCommand changeKeyCommand);
}
