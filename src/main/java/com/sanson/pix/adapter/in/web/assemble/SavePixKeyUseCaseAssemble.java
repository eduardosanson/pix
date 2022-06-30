package com.sanson.pix.adapter.in.web.assemble;

import com.sanson.pix.adapter.in.web.dto.CreatePixDTO;
import com.sanson.pix.application.port.in.SavePixKeyCommand;

public class SavePixKeyUseCaseAssemble {

    public static SavePixKeyCommand assemble(CreatePixDTO createPixDTO){

        var holder = HolderAssemble.assemble(createPixDTO.getHolderName(),
                createPixDTO.getHolderLastName(),createPixDTO.getHolderType());

        return new SavePixKeyCommand(createPixDTO.getPixType(),createPixDTO.getPixValue(), createPixDTO.getAccountType(),
                createPixDTO.getAgencyNumber(), createPixDTO.getAccountNumber(), holder);

    }
}
