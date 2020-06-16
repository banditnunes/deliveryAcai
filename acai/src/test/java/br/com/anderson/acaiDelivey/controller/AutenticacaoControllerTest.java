package br.com.anderson.acaiDelivey.controller;



import br.com.anderson.acaiDelivey.AcaiDeliveyApplicationTests;
import br.com.anderson.acaiDelivey.form.LoginForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasLength;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AutenticacaoControllerTest extends AcaiDeliveyApplicationTests {

    //Url Base
    private static final String BASE_URL = "/auth";

    //Instancia para trabalhar com JSON
    private ObjectMapper objectMapper;

    //Controller que será testado
    @Autowired
    AutenticacaoController autenticacaoController;

    //Instancia do MockMvc
    private MockMvc mockMvc;


    @Before
    public void config() {
        this.objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(autenticacaoController)
                .build();
    }

    @Test
    public void autentica_usuario_with_body_then_return_success() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setLogin("cliente@email.com");
        loginForm.setSenha("123456");

        mockMvc.perform(
                post(BASE_URL)
                        .content(objectMapper.writeValueAsString(loginForm))
                        .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token", hasLength(161)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipo", containsString("Bearer")))
        ;
    }

    @Test
    public void autentica_usuario_without_body_then_return_bad_request() throws Exception {
        mockMvc.perform(
                post(BASE_URL)
                        .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());
    }


    @Test
    public void autentica_usuario_with_url_wrong_then_return_notfound() throws Exception {
        mockMvc.perform(
                post(BASE_URL + 2)
                        .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());
    }
    @Test
    public void autentica_usuario_with_user_wrong_then_return_notfound() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setLogin("clientes@email.com");
        loginForm.setSenha("123456");

        mockMvc.perform(
                post(BASE_URL+2)
                        .content(objectMapper.writeValueAsString(loginForm))
                        .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON))
                .andExpect(status().isNotFound())

        ;
    }


}
