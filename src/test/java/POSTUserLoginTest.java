import io.restassured.response.Response;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static requests.LoginEndsPoints.postLoginRequest;
import static requests.UserEndPoints.deleteUserRequest;
import static requests.UserEndPoints.postUserRequest;

public class POSTUserLoginTest extends TestBase {
private User validUser1;
    @BeforeClass
    public void generateTestData() {

        validUser1 = new User("ANaa", "blacklist11183@gmail.com", "1234@A", "false");
        postUserRequest(SPEC, validUser1);


    }
    @Test
    public void shouldReturnSuccessMessageAndStatusCode200(){
            Response loginUserResponse = postLoginRequest(SPEC, validUser1);
            loginUserResponse.
                    then().
                    assertThat().
                    statusCode(200).
                    body("message",equalTo("Login realizado com sucesso")).

                    body("authorization",notNullValue());
        }


    @AfterClass
    public void removeTestData(){
        deleteUserRequest(SPEC,validUser1);    }
}
