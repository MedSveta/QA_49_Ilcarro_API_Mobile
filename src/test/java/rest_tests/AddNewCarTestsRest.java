package rest_tests;

import dto.CarDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import rest_api.CarController;

import java.util.Random;

public class AddNewCarTestsRest extends CarController {

    @Test
    public void addNewCarPositiveTest() {
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("876-" + i)
                .manufacture("Honda")
                .model("CRV")
                .year("2024")
                .fuel("Electric")
                .seats(4)
                .carClass("A")
                .pricePerDay(23.5)
                .city("Haifa")
                .build();
        Response response = addNewCar(car);
        System.out.println(response.getBody().print());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
