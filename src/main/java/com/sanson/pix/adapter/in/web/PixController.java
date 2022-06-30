package com.sanson.pix.adapter.in.web;

import com.sanson.pix.adapter.in.web.assemble.AccountAssemble;
import com.sanson.pix.adapter.in.web.assemble.ChangeKeyUseAssemble;
import com.sanson.pix.adapter.in.web.assemble.SavePixKeyUseCaseAssemble;
import com.sanson.pix.adapter.in.web.dto.CreatePixDTO;
import com.sanson.pix.adapter.in.web.dto.DeletePixResponseDTO;
import com.sanson.pix.adapter.in.web.dto.IdResponseDTO;
import com.sanson.pix.adapter.in.web.dto.PixResponseDTO;
import com.sanson.pix.adapter.in.web.dto.UpdatePixDTO;
import com.sanson.pix.adapter.in.web.dto.UpdatePixResponseDTO;
import com.sanson.pix.application.port.in.ChangeKeyUseCase;
import com.sanson.pix.application.port.in.DisableKeyUseCase;
import com.sanson.pix.application.port.in.FindKeyUseCase;
import com.sanson.pix.application.port.in.SavePixKeyUseCase;
import com.sanson.pix.domain.NotFoundException;
import com.sanson.pix.domain.managerPix.pixKeys.PixType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pix")
public class PixController {

    private ChangeKeyUseCase changeKeyUseCase;

    private SavePixKeyUseCase savePixKeyUseCase;

    private FindKeyUseCase findKeyUseCase;

    private DisableKeyUseCase disableKeyUseCase;

    public PixController(ChangeKeyUseCase changeKeyUseCase,
                         SavePixKeyUseCase savePixKeyUseCase,
                         FindKeyUseCase findKeyUseCase,
                         DisableKeyUseCase disableKeyUseCase) {
        this.changeKeyUseCase = changeKeyUseCase;
        this.savePixKeyUseCase = savePixKeyUseCase;
        this.findKeyUseCase = findKeyUseCase;
        this.disableKeyUseCase = disableKeyUseCase;
    }

    @PostMapping
    public ResponseEntity<IdResponseDTO> create(@RequestBody CreatePixDTO createPixDTO){
        var id = savePixKeyUseCase.saveKey(SavePixKeyUseCaseAssemble.assemble(createPixDTO)).getId();
        return new ResponseEntity(new IdResponseDTO(id.toString()), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdatePixResponseDTO> update(@PathVariable UUID id,
                                                       @RequestBody UpdatePixDTO updatePixDTO){
        var account = changeKeyUseCase
                .changeKey(ChangeKeyUseAssemble.assemble(id, updatePixDTO));
        return ResponseEntity.ok(ChangeKeyUseAssemble.dissemble(account,id));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<DeletePixResponseDTO> delete(@PathVariable String id){
        var uuid = UUID.fromString(id);
        var account = disableKeyUseCase.disableKey(uuid);
        return ResponseEntity.ok(AccountAssemble.dissembleToDeletePixResponse(uuid, account));
    }

    @GetMapping("{id}")
    public ResponseEntity<PixResponseDTO> findOne(@PathVariable String id){
        var uuid = UUID.fromString(id);
        var account = findKeyUseCase.findKey(uuid);
        return ResponseEntity.ok(AccountAssemble.dissembleToPixResponse(uuid, account));
    }

    @GetMapping
    public ResponseEntity<List<PixResponseDTO>> findMany(
            @RequestParam Integer agency,
            @RequestParam Integer accountNumber){
        var account = findKeyUseCase.findKey(agency, accountNumber);
        return ResponseEntity.ok(AccountAssemble.dissembleToPixResponse(account));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<PixResponseDTO>> findMany(@PathVariable PixType type){
        var account = findKeyUseCase.findKeyType(type);

        return ResponseEntity.ok(AccountAssemble.dissembleToPixResponse(type, account));
    }


}


