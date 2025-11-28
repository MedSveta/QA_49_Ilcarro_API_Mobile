package rest_api;

import dto.RegistrationBodyDto;
import interfaces.Base_Api;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationController implements Base_Api {

    public Response registrationLogin(RegistrationBodyDto user, String url) {
        return given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + url)
                .thenReturn();
    }
}
