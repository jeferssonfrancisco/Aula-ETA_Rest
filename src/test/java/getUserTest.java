import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import  org.testng.annotations.Test;
import java.util.List;
import static requests.UserEndPoints.*;
import static org.hamcrest.Matchers.*;




public class getUserTest extends TestBase {
    private User validUser1;
    private User validUser2;

    @BeforeClass
    public void generateTestData() {
        validUser1 = new User("ANaa", "blacklist11183@gmail.com", "1234@A", "false");
        postUserRequest(SPEC, validUser1);

        validUser2 = new User("Pedroo", "pedreone201183@gmail.com", "1234@A", "false");
        postUserRequest(SPEC, validUser2);
    }

    @DataProvider(name = "userQueryData")
    public Object[][] createQueryData(){
        return new Object[][]{
                {"nome", validUser1.nome},
                {"email", validUser2.email}
        };
    }

    @Test
    public void shouldReturnAllUsersAndStatusCode200() {
        Response getUsersResponse = getUsersRequest(SPEC);
        getUsersResponse.

        then().
                assertThat().
                statusCode(200).
                body("quantidade", equalTo(1)).
                body("quantidade", instanceOf(Integer.class)).
                body("usuarios",instanceOf(List.class));


    }


    @Test (dataProvider = "userQueryData")
    public void shouldReturnUsersForQueryAndStatusCode200(String query, String queryValue) {

        SPEC.queryParam(query, queryValue);
        Response getUsersResponse = getUsersRequest(SPEC);
        getUsersResponse.then().log().all();

        FilterableRequestSpecification filterableRequestSpecification = (FilterableRequestSpecification) SPEC;
        filterableRequestSpecification.removeQueryParam(query);

    }


    @AfterClass
    public void removeTestData(){

        deleteUserRequest(SPEC, validUser1);
        deleteUserRequest(SPEC, validUser2);
    }

}
