package com.sanson.pix.adapter.in.web.assemble;

import com.sanson.pix.adapter.in.web.dto.DeletePixResponseDTO;
import com.sanson.pix.adapter.in.web.dto.PixResponseDTO;
import com.sanson.pix.domain.managerPix.Account;
import com.sanson.pix.domain.managerPix.pixKeys.PixKey;
import com.sanson.pix.domain.managerPix.pixKeys.PixType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        return toPixResponseDTO(account, pixKey);
    }

    public static List<PixResponseDTO> dissembleToPixResponse(Account account){
        return account.getPixKeys().stream()
                .map((pixKey) ->toPixResponseDTO(account, pixKey))
                .collect(Collectors.toList());

    }

    public static List<PixResponseDTO> dissembleToPixResponse(PixType type, List<Account> accounts) {
        List<PixResponseDTO> pixResponses = new ArrayList<>();
        accounts.forEach(account -> {
            account.getPixKeys().stream()
                    .filter(key -> key.getType().equals(type))
                    .forEach(key -> pixResponses.add(toPixResponseDTO(account, key)));

        });
        return pixResponses;
    }

    private static PixResponseDTO toPixResponseDTO(Account account, PixKey pixKey) {
        return new PixResponseDTO(pixKey.getId().toString(), pixKey.getType(), pixKey.getValue(), account.getType(),
                account.getAgency(), account.getNumber(), account.getHolder().getName(),
                account.getHolder().getLastName(), account.getHolder().getType(),
                pixKey.getCreatedAt().toLocalDate(), pixKey.getDisabledAtAsDate());
    }
}
