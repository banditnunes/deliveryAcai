package br.com.anderson.acaiDelivey.controller;


import br.com.anderson.acaiDelivey.DTO.TokenDTO;
import br.com.anderson.acaiDelivey.form.LoginForm;
import br.com.anderson.acaiDelivey.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;
    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginForm autenticacaoForm) {

        UsernamePasswordAuthenticationToken dadosLogin = autenticacaoForm.converter();
        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authenticate);
            //Retorna o token para o cliente
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));

        } catch (AuthenticationException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}

