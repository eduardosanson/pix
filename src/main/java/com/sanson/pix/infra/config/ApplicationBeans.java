package com.sanson.pix.infra.config;

import com.sanson.pix.application.port.in.ChangeKeyUseCase;
import com.sanson.pix.application.port.in.DisableKeyUseCase;
import com.sanson.pix.application.port.in.FindKeyUseCase;
import com.sanson.pix.application.port.in.SavePixKeyUseCase;
import com.sanson.pix.application.port.out.AccountPort;
import com.sanson.pix.application.port.out.UpdateKeyPort;
import com.sanson.pix.application.usecase.ChangeKeyService;
import com.sanson.pix.application.usecase.DisableKeyService;
import com.sanson.pix.application.usecase.FindKeyService;
import com.sanson.pix.application.usecase.SavePixKeyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeans {

    @Bean
    public ChangeKeyUseCase changeKeyUseCase(AccountPort accountPort){
        return new ChangeKeyService(accountPort);
    }

    @Bean
    public SavePixKeyUseCase savePixKeyUseCase(AccountPort accountPort){
        return new SavePixKeyService(accountPort);
    }

    @Bean
    public DisableKeyUseCase disableKeyUseCase(AccountPort accountPort, UpdateKeyPort updateKeyPort){
        return new DisableKeyService(accountPort,updateKeyPort);
    }

    @Bean
    public FindKeyUseCase findKeyUseCase(AccountPort accountPort){
        return new FindKeyService(accountPort);
    }
}
