package com.sanson.pix.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sanson.pix.adapter.in.web.assemble.ChangeKeyUseAssemble;
import com.sanson.pix.adapter.in.web.dto.CreatePixDTO;
import com.sanson.pix.adapter.in.web.dto.UpdatePixDTO;
import com.sanson.pix.application.port.in.ChangeKeyCommand;
import com.sanson.pix.application.port.in.ChangeKeyUseCase;
import com.sanson.pix.application.port.in.DisableKeyUseCase;
import com.sanson.pix.application.port.in.FindKeyUseCase;
import com.sanson.pix.application.port.in.SavePixKeyCommand;
import com.sanson.pix.application.port.in.SavePixKeyUseCase;
import com.sanson.pix.domain.BusinessException;
import com.sanson.pix.domain.NotFoundException;
import com.sanson.pix.domain.managerPix.AccountType;
import com.sanson.pix.domain.managerPix.HolderType;
import com.sanson.pix.domain.managerPix.pixKeys.PixType;
import com.sanson.pix.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PixController.class)
class PixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChangeKeyUseCase changeKeyUseCase;

    @MockBean
    private SavePixKeyUseCase savePixKeyUseCase;

    @MockBean
    private FindKeyUseCase findKeyUseCase;

    @MockBean
    private DisableKeyUseCase disableKeyUseCase;

    private static final String BASE_URL = "/pix";

    @Test
    public void shouldReturnPixKeyId() throws Exception {
        var request = new CreatePixDTO(PixType.EMAIL, "teste@teste.com", AccountType.CHECKING, 1234, 12345,
                "holderTest", "", HolderType.F);

        var pixKey = TestUtil.validEmail();

        when(savePixKeyUseCase.saveKey(any(SavePixKeyCommand.class))).thenReturn(pixKey);

        this.mockMvc.perform(
                post(BASE_URL)
                        .contentType(APPLICATION_JSON)
                        .content(TestUtil.convertToJson(request))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(pixKey.getId().toString())));
    }

    @Test
    public void shouldReturnUnprocessableEntityIfThrowsBusiness() throws Exception {
        var request = new CreatePixDTO(PixType.EMAIL, "testeteste.com", AccountType.CHECKING,
                1234, 12345,"holderTest", "", HolderType.F);

        when(savePixKeyUseCase.saveKey(any(SavePixKeyCommand.class)))
                .thenThrow(new BusinessException("test with exception"));

        this.mockMvc.perform(
                        post(BASE_URL)
                                .contentType(APPLICATION_JSON)
                                .content(TestUtil.convertToJson(request))
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString("test with exception")));
    }

    @Test
    public void shouldReturnOkWheUpdatePixKey() throws Exception {

        var account = TestUtil.validAccount();
        var pixKey = TestUtil.validEmail();
        account.addNewPixKey(pixKey);
        var request = new UpdatePixDTO(pixKey.getId().toString(), AccountType.CHECKING, 1234,
                12345, "holderTest", "", HolderType.F);


        when(changeKeyUseCase.changeKey(any(ChangeKeyCommand.class))).thenReturn(account);

        this.mockMvc.perform(
                        put(BASE_URL)
                                .contentType(APPLICATION_JSON)
                                .content(TestUtil.convertToJson(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(account.getId().toString())))
                .andExpect(jsonPath("$.pixType", is("EMAIL")))
                .andExpect(jsonPath("$.pixValue", is(pixKey.getValue())))
                .andExpect(jsonPath("$.accountType", is("CHECKING")))
                .andExpect(jsonPath("$.agencyNumber", is(account.getAgency())))
                .andExpect(jsonPath("$.accountNumber", is(account.getNumber())))
                .andExpect(jsonPath("$.holderName", is(account.getHolder().getName())))
                .andExpect(jsonPath("$.holderLastName", is(account.getHolder().getLastName())))
                .andExpect(jsonPath("$.holderType", is("F")));
    }

    @Test
    public void shouldReturnNotFoundStatus() throws Exception {

        var account = TestUtil.validAccount();
        var pixKey = TestUtil.validEmail();
        account.addNewPixKey(pixKey);
        var request = new UpdatePixDTO(pixKey.getId().toString(), AccountType.CHECKING, 1234,
                12345, "holderTest", "", HolderType.F);


        when(changeKeyUseCase.changeKey(any(ChangeKeyCommand.class))).thenThrow(new NotFoundException("teste not found"));

        this.mockMvc.perform(
                        put(BASE_URL)
                                .contentType(APPLICATION_JSON)
                                .content(TestUtil.convertToJson(request))
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("teste not found")));
    }

    @Test
    public void shouldReturnOkWheDisablePixKey() throws Exception {

        var account = TestUtil.validAccount();
        var pixKey = TestUtil.validEmail();
        account.addNewPixKey(pixKey);

        when(disableKeyUseCase.disableKey(any(UUID.class))).thenReturn(account);

        this.mockMvc.perform(
                        delete(BASE_URL + "/" + pixKey.getId().toString())
                                .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(pixKey.getId().toString())))
                .andExpect(jsonPath("$.pixType", is("EMAIL")))
                .andExpect(jsonPath("$.pixValue", is(pixKey.getValue())))
                .andExpect(jsonPath("$.accountType", is("CHECKING")))
                .andExpect(jsonPath("$.agencyNumber", is(account.getAgency())))
                .andExpect(jsonPath("$.accountNumber", is(account.getNumber())))
                .andExpect(jsonPath("$.holderName", is(account.getHolder().getName())))
                .andExpect(jsonPath("$.holderLastName", is(account.getHolder().getLastName())))
                .andExpect(jsonPath("$.holderType", is("F")))
                .andExpect(jsonPath("$.createdAt", anything()))
                .andExpect(jsonPath("$.disabledAt", anything()));
    }

    @Test
    public void shouldReturnOkWhenFindPixKey() throws Exception {

        var account = TestUtil.validAccount();
        var pixKey = TestUtil.validEmail();
        pixKey.disable();
        account.addNewPixKey(pixKey);

        when(findKeyUseCase.findKey(any(UUID.class))).thenReturn(account);

        this.mockMvc.perform(
                        get(BASE_URL + "/" + pixKey.getId().toString())
                                .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(pixKey.getId().toString())))
                .andExpect(jsonPath("$.pixType", is("EMAIL")))
                .andExpect(jsonPath("$.pixValue", is(pixKey.getValue())))
                .andExpect(jsonPath("$.accountType", is("CHECKING")))
                .andExpect(jsonPath("$.agencyNumber", is(account.getAgency())))
                .andExpect(jsonPath("$.accountNumber", is(account.getNumber())))
                .andExpect(jsonPath("$.holderName", is(account.getHolder().getName())))
                .andExpect(jsonPath("$.holderLastName", is(account.getHolder().getLastName())))
                .andExpect(jsonPath("$.holderType", is("F")))
                .andExpect(jsonPath("$.createdAt", anything()));
    }


}