package requests;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models. Produtos;
import static io.restassured.RestAssured.given;



public class ProductEndPoint extends RequestBase{

        public static Response getProdutosRequest(RequestSpecification spec) {
            Response getProdutosResponse =
                    given().
                            spec(spec).

                            when().

                            get("/produtos");
            return getProdutosResponse;

        }

        public static Response postProdutosRequest(RequestSpecification spec, Produtos produtos) {

            Gson gson = new Gson();
            String produtosJsonRepresentation = gson.toJson(produtos);

            Response postProdutosResponse =
                    given().
                            spec(spec).
                            header("Content-Type", "application/json").
                            and().
                            body(produtosJsonRepresentation).

                            when().
                            post("/produtos");
            produtos.setProdutosId(getValueFromResponse(postProdutosResponse,"_id"));

            return postProdutosResponse;


        }


        public static Response deleteProdutosRequest(RequestSpecification spec, Produtos produtos) {

            Response deleteProdutosResponse =
                    given().
                            spec(spec).
                            pathParam("_id",produtos._id="K6leHdftCeOJj8BJ").

                            when().
                            delete("/produtos");


            return deleteProdutosResponse;
        }
    }

