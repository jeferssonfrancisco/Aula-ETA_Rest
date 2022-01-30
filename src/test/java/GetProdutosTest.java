import io.restassured.response.Response;
import models.Produtos;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static requests.ProductEndPoint.*;


public class GetProdutosTest extends TestBase{

    private Produtos validProdutos1;

    @BeforeClass
    public void generateTestData(){
        validProdutos1 = new Produtos("cell",1,"eletronico",10,"K6leHdftCeOJj8BJ");
        postProdutosRequest(SPEC,validProdutos1);
    }

        @Test

        public void shouldReturnAllProdutosAndStatusCode200() {
            Response getProdutosResponse = getProdutosRequest(SPEC);
            getProdutosResponse.

                    then().
                    assertThat().
                    statusCode(200).
                    body("quantidade", equalTo(2)).
                    body("quantidade", instanceOf(Integer.class)).
                    body("produtos",instanceOf(List.class));
        }

       @Test
        public void shouldReturnAllProdutosAndStatusCode400(){
            Response getProdutosResponse = getProdutosRequest(SPEC);
            getProdutosResponse.

                then().
                assertThat().
                statusCode(400).
                body("quantidade",equalTo(2)).
                    body("quantidade", instanceOf(Integer.class)).
                    body("produtos",instanceOf(List.class));

        }

        @AfterClass
        public void removeTestData(){

            deleteProdutosRequest(SPEC, validProdutos1);

        }




    }
