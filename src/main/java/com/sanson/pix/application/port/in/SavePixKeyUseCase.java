package com.sanson.pix.application.port.in;

import com.sanson.pix.domain.managerPix.pixKeys.PixKey;

public interface SavePixKeyUseCase {

    PixKey saveKey(SavePixKeyCommand savePixKeyCommand);
}
