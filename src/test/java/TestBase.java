import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
public class TestBase {

    public RequestSpecification SPEC = new RequestSpecBuilder()
            .addHeader("accept", "application/json")
            .setBaseUri("http://localhost:3000").build();





}

