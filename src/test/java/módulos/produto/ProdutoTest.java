package módulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest - Módulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeESach() {
        //Configurando os dados da API Rest da Lojinha
        baseURI = "http://165.227.93.41";
        //port = 8080;
        basePath = "/lojinha";

        //Obter o token do usuario admin
        this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.criarUsuarioAdministrador())
        .when()
                .post("/v2/login")

        .then()
            .extract()
                .path("data.token");

    }

    @Test
    @DisplayName("Validar os valores inválidos do valor do Produto - Valores iguais a Zero")
    public void testValidarLimiteZeradoProibidoValorProduto() {

        given()
                .contentType(ContentType.JSON)
                .header("token", token)
                .body(ProdutoDataFactory.criarProdutoComumComUmValorIgualA(0.00))
            .when()
                .post("/v2/produtos")

            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);

    }

    @Test
    @DisplayName("Validar os valores inválidos do valor do Produto - Valores acima de Sete Mil")
    public void testValidarLimiteMaiorSeteMilProibidoValorProduto() {

        given()
                .contentType(ContentType.JSON)
                .header("token", token)
                .body(ProdutoDataFactory.criarProdutoComumComUmValorIgualA(7000.01))
            .when()
                .post("/v2/produtos")

            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
    }
}
