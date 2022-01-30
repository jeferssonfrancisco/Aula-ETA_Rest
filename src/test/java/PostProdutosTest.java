import io.restassured.response.Response;
import models.Produtos;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static requests.ProductEndPoint.deleteProdutosRequest;
import static requests.ProductEndPoint.postProdutosRequest;


public class PostProdutosTest extends TestBase {
    private Produtos validProdutos1;


    @BeforeClass
    public void generateTestData(){
        validProdutos1 = new Produtos("cell",1,"eletronico",10,"cbv");
        postProdutosRequest(SPEC,validProdutos1);
    }

    @Test
    public void shouldReturnSuccessMessageAndStatusCode201(){
        Response postProdutosResponse = postProdutosRequest(SPEC, validProdutos1);
                postProdutosResponse.
                then().
                assertThat().
                statusCode(200).
                body("message",equalTo("Cadastro realizado com sucesso")).

                body("authorization",notNullValue());
    }

    @Test
    public void shouldReturnSuccessMessageAndStatusCode401(){
        Response postProdutosResponse = postProdutosRequest(SPEC, validProdutos1);
        postProdutosResponse.
                then().
                assertThat().
                statusCode(401).
                body("message",equalTo("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais")).

                body("authorization",null);
    }

    @AfterClass
    public void removeTestData(){
        deleteProdutosRequest(SPEC,validProdutos1);
    }


}
