package com.sanson.pix.adapter.in.web.assemble;

import com.sanson.pix.adapter.in.web.dto.DeletePixResponseDTO;
import com.sanson.pix.adapter.in.web.dto.PixResponseDTO;
import com.sanson.pix.domain.managerPix.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountAssemble {

    public static DeletePixResponseDTO dissembleToDeletePixResponse(UUID id, Account account){
        var pixKey = account.getPixKeyById(id).get();

        return new DeletePixResponseDTO(pixKey.getId().toString(), pixKey.getType(), pixKey.getValue(), account.getType(),
                account.getAgency(), account.getNumber(), account.getHolder().getName(),
                account.getHolder().getLastName(), account.getHolder().getType(),
                pixKey.getCreatedAt(), pixKey.getDisabledAt());
    }

    public static PixResponseDTO dissembleToPixResponse(UUID id, Account account){
        var pixKey = account.getPixKeyById(id).get();

        return new PixResponseDTO(pixKey.getId().toString(), pixKey.getType(), pixKey.getValue(), account.getType(),
                account.getAgency(), account.getNumber(), account.getHolder().getName(),
                account.getHolder().getLastName(), account.getHolder().getType(),
                pixKey.getCreatedAt().toLocalDate(), pixKey.getDisabledAtAsDate());
    }

    public static List<PixResponseDTO> dissembleToPixResponse(Account account){
        List<PixResponseDTO> pixResponses = new ArrayList<>();

        account.getPixKeys().forEach((pixKey) -> {
            pixResponses.add(
                    new PixResponseDTO(pixKey.getId().toString(), pixKey.getType(), pixKey.getValue(), account.getType(),
                            account.getAgency(), account.getNumber(), account.getHolder().getName(),
                            account.getHolder().getLastName(), account.getHolder().getType(),
                            pixKey.getCreatedAt().toLocalDate(), pixKey.getDisabledAtAsDate())
            );
        });

        return pixResponses;
    }
}
