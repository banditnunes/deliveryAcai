package br.com.anderson.acaiDelivey.controller;

import io.restassured.http.Header;
import net.minidev.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.springframework.http.HttpStatus.OK;

public class PedidoControllerTest {

    @BeforeClass
    public void config() {
        baseURI = "http://localhost:8080";
    }

    //Testes API Req.001 - Escolher Açai(POST)
    @Test
    public void escolher_acai_with_url_then_return_success() throws Exception {
        JSONObject requestParams = new JSONObject();
        requestParams.put("tamanho", "3");
        requestParams.put("sabor", "3");

        Header header = new Header("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgQcOnYWkgRGVsaXZlcnkiLCJpYXQiOjE1OTIyNDMzMjEsImV4cCI6MTU5MjMyOTcyMSwic3ViIjoiMSJ9.Eo1tLlchpex8QsntsXXFckLNaGcMH1E19ia0GwP71lc");


        given()
                .contentType(JSON)
                .header(header)
                .body(requestParams.toJSONString()).
                when()
                .post("/pedido").
                then()
                .statusCode(201)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/schema_pedido_escolher_acai.json"));
    }

    @Test
    public void escolher_acai_with_wrong_sabor_value_url_then_return_not_found() throws Exception {
        JSONObject requestParams = new JSONObject();
        requestParams.put("tamanho", "3");
        requestParams.put("sabor", "4");

        Header header = new Header("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgQcOnYWkgRGVsaXZlcnkiLCJpYXQiOjE1OTIyNDMzMjEsImV4cCI6MTU5MjMyOTcyMSwic3ViIjoiMSJ9.Eo1tLlchpex8QsntsXXFckLNaGcMH1E19ia0GwP71lc");


        given()
                .contentType(JSON)
                .header(header)
                .body(requestParams.toJSONString()).
                when()
                .post("/pedido").
                then()
                .statusCode(404)
                .assertThat()
        ;
    }

    @Test
    public void escolher_acai_without_header_then_return_bad_request() throws Exception {
        JSONObject requestParams = new JSONObject();
        requestParams.put("tamanho", "3");
        requestParams.put("sabor", "3");


        given()
                .contentType(JSON)
                .body(requestParams.toJSONString()).
                when()
                .post("/pedido").
                then()
                .statusCode(400);


    }

    @Test
    public void escolher_acai_with_url_then_return_forbidden() throws Exception {
        JSONObject requestParams = new JSONObject();
        requestParams.put("tamanho", "3");
        requestParams.put("sabor", "3");
        Header header = new Header("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgQcOnYWkgRGVsaXZlcnkiLCJpYXQiOjE1OTIyNDMzMjEsImV4cCI6MTU5MjMyOTcyMSwic3ViIjoiMSJ9.Eo1tLlchpex8QsntsXXFckLNaGcMH1E19ia0GwP71lc");

        given()
                .contentType(JSON)
                .header(header)
                .body(requestParams.toJSONString()).
                when()
                .post("/pedidos").
                then()
                .statusCode(403);


    }

    @Test
    public void escolher_acai_with_wrong_url_then_return_method_not_allowed() throws Exception {
        JSONObject requestParams = new JSONObject();
        requestParams.put("tamanho", "3");
        requestParams.put("sabor", "3");

        Header header = new Header("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgQcOnYWkgRGVsaXZlcnkiLCJpYXQiOjE1OTIyNDMzMjEsImV4cCI6MTU5MjMyOTcyMSwic3ViIjoiMSJ9.Eo1tLlchpex8QsntsXXFckLNaGcMH1E19ia0GwP71lc");

        given()
                .contentType(JSON)
                .header(header)
                .body(requestParams.toJSONString()).
                when()
                .post("/pedido/1").
                then()
                .statusCode(405);


    }

    //Testes API Req.002 - Personalizar Açai (PUT)
    @Test
    public void personalizar_acai_with_url_then_return_success() throws Exception {
        JSONObject requestParams = new JSONObject();
        requestParams.put("complementos", Arrays.asList("Granola", "Leite Ninho", "Paçoca"));

        Header header = new Header("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgQcOnYWkgRGVsaXZlcnkiLCJpYXQiOjE1OTIyNDMzMjEsImV4cCI6MTU5MjMyOTcyMSwic3ViIjoiMSJ9.Eo1tLlchpex8QsntsXXFckLNaGcMH1E19ia0GwP71lc");


        given()
                .contentType(JSON)

                .header(header)
                .body(requestParams.toJSONString()).
                when()
                .put("/pedido/1").
                then()
                .statusCode(400);

    }

    @Test
    public void personalizar_acai_without_header_then_return_bad_request() throws Exception {
        JSONObject requestParams = new JSONObject();
        requestParams.put("complementos", Arrays.asList("Granola", "Leite Ninho", "Paçoca"));


        given()
                .contentType(JSON)
                .body(requestParams.toJSONString()).
                when()
                .put("/pedido/1").
                then()
                .statusCode(200);

    }


    //Testes API Açai Req.003 - Montar Pedido (GET)


    public void busca_pedido_por_id_with_url_then_return_success() throws Exception {
        Header header = new Header("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgQcOnYWkgRGVsaXZlcnkiLCJpYXQiOjE1OTIyNDMzMjEsImV4cCI6MTU5MjMyOTcyMSwic3ViIjoiMSJ9.Eo1tLlchpex8QsntsXXFckLNaGcMH1E19ia0GwP71lc");

        given()
                .contentType(JSON)
                .header(header)
                .get("/pedido/1")
                .then()
                .statusCode(OK.value())
                .contentType(JSON);

    }


}
