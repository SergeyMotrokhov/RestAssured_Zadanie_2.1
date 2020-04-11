import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class MainTest {
    @Test
    public void jsobArrayTest(){
    }


    @Test
    public void checkSuccessRegister(){

        Map<String,String> data = new HashMap<String, String>();
        data.put("email","eve.holt@reqres.in");
        data.put("password","pistol");

        Response newUser =given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .statusCode(200)
                .extract()
                .response();
    }
    @Test
    public void checkWrongPassword(){
        Map<String,String> data = new HashMap<String, String>();
        data.put("email","eve.holt@reqres.in");
        data.put("password","");
        Response newUser = given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .body("error", equalTo("Missing password"))
                .statusCode(400).assertThat().body("error",equalTo("Missing password"))

                .extract()
                .response();
        JsonPath jsonNewUser = newUser.jsonPath();

    }

}
