package rest_tests;

import dto.ErrorMessageDto;
import dto.RegistrationBodyDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import rest_api.AuthenticationController;

import java.util.Random;

public class RegistrationTestsRest extends AuthenticationController {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("poly_polina" + i + "@gmail.com")
                .password("AAaa123!")
                .firstName("Poly")
                .lastName("Polina")
                .build();
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL)
                .getStatusCode(), 200);
    }

    //poly_polina"+i+"gmail.com; @gmail.com; poly_polina@w;
    //poly_polina@@gmail.com; poly_polina@w.; poly_polina@.r;
    //poly_polina@.r; poly_polina"+11111111111+"gmail.com;
    //poly polina@gmail.com; полина"+i+"polina@gmail.com;
    //poly_polina"+i+"@gmail..com;
    @Test
    public void registrationNegativeTest_WrongEmail() {
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("poly_polina" + i + "gmail.com")
                .password("AAaa123!")
                .firstName("Poly")
                .lastName("Polina")
                .build();
        Response response = registrationLogin(user, REGISTRATION_URL);
        System.out.println(response.getBody().print());
        softAssert.assertEquals(response.getStatusCode(), 400,
                "validate statusCode");
        ErrorMessageDto errorMessageDto = response.getBody()
                .as(ErrorMessageDto.class);
        softAssert.assertEquals(errorMessageDto.getError(),
                "Bad Request", "validate ErrorName");
        softAssert.assertTrue(errorMessageDto.getMessage().toString()
                .contains("a well-formed email address"), "validate Message");
        softAssert.assertAll();
    }
}
