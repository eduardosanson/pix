package com.sanson.pix.application.port.out;

import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.pixKeys.PixKey;

public interface UpdateKeyPort {

    PixKey updateKey(PixKey pixKey);
}
