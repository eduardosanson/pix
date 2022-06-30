package com.sanson.pix.adapter.in.web.assemble;

import com.sanson.pix.adapter.in.web.dto.UpdatePixDTO;
import com.sanson.pix.adapter.in.web.dto.UpdatePixResponseDTO;
import com.sanson.pix.application.port.in.ChangeKeyCommand;
import com.sanson.pix.domain.managerPix.Account;

import java.util.Optional;
import java.util.UUID;

public class ChangeKeyUseAssemble {

    public static ChangeKeyCommand assemble(UpdatePixDTO updatePixDTO){

        return new ChangeKeyCommand(
                updatePixDTO.getId(),
                Optional.ofNullable(updatePixDTO.getAccountType()),
                Optional.ofNullable(updatePixDTO.getAgencyNumber()),
                Optional.ofNullable(updatePixDTO.getAccountNumber()),
                Optional.ofNullable(updatePixDTO.getHolderType()),
                Optional.ofNullable(updatePixDTO.getHolderName()),
                Optional.ofNullable(updatePixDTO.getHolderLastName()));
    }

    public static UpdatePixResponseDTO dissemble(Account account, UUID id){
        var pixKey = account.getPixKeyById(id).get();

        return new UpdatePixResponseDTO(account.getId().toString(), pixKey.getType(), pixKey.getValue(),
                account.getType(), account.getAgency(), account.getNumber(),account.getHolder().getName(),
                account.getHolder().getLastName(), account.getHolder().getType(),pixKey.getCreatedAt());
    }
}
