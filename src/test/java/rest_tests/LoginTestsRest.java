package rest_tests;

import dto.RegistrationBodyDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rest_api.AuthenticationController;

import java.util.Random;

public class LoginTestsRest extends AuthenticationController {
    RegistrationBodyDto user;

    @BeforeMethod
    public void registrationUser() {
        int i = new Random().nextInt(1000);
        user = RegistrationBodyDto.builder()
                .username("poly_polina" + i + "@gmail.com")
                .password("AAaa123!")
                .firstName("Poly")
                .lastName("Polina")
                .build();
        System.out.println("result -->"
                + registrationLogin(user, REGISTRATION_URL)
                .getStatusCode());
        System.out.println(user);
    }

    @Test
    public void loginPositiveTest() {
        Assert.assertEquals(registrationLogin(user, LOGIN_URL)
                .getStatusCode(), 200);
    }

    @Test
    public void loginNegativeTest_UnregisteredUser() {
        user.setUsername("pljihg165@mdfr.cu");
        Response response = registrationLogin(user, LOGIN_URL);
        System.out.println(response.getBody().print());
        Assert.assertEquals(response.getStatusCode(),
                401);
    }
}
