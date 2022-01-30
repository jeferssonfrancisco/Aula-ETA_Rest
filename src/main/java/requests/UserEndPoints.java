package requests;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;
import static io.restassured.RestAssured.given;

public class UserEndPoints extends RequestBase {


    public static Response getUsersRequest(RequestSpecification spec) {
        Response getUsersResponse =
                given().
                        spec(spec).

                        when().

                        get("/usuarios");
        return getUsersResponse;

    }

    public static Response postUserRequest(RequestSpecification spec, User user) {

        Gson gson = new Gson();
        String userJsonRepresentation = gson.toJson(user);

        Response postUserResponse =
                given().
                        spec(spec).
                        header("Content-Type", "application/json").
                        and().
                        body(userJsonRepresentation).

                        when().
                                post("/usuarios");
        user.setUserId(getValueFromResponse(postUserResponse, "_id"));

    return postUserResponse;


    }


    public static Response deleteUserRequest(RequestSpecification spec, User user) {

        Response deleteUserResponse =
                given().
                        spec(spec).
                            pathParam("_id",user._id).

                        when().
                       delete("/usuarios/{_id}");


        return deleteUserResponse;
    }
}