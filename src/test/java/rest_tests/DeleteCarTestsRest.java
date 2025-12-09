package rest_tests;

import dto.CarsDto;
import dto.ErrorMessageDto;
import dto.ResponseMessageDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import rest_api.CarController;

public class DeleteCarTestsRest extends CarController {
    SoftAssert softAssert = new SoftAssert();
    CarsDto cars;

    @BeforeClass
    public void getAllUserCarsBeforeDelete() {
        Response response = getAllUserCars();
        if (response.getStatusCode() == 200) {
            cars = response.getBody().as(CarsDto.class);
        } else
            System.out.println("status code isn't 200 "
                    + response.getStatusCode());
    }

    @Test
    public void deleteCarPositiveTest(){
        if(cars.getCars().length != 0){
            String serialNumber = cars.getCars()[0].getSerialNumber();
            System.out.println(serialNumber);
            Response response = deleteCarBySerialNumber(serialNumber);
            System.out.println(response.getBody().print());
            ResponseMessageDto responseMessageDto = response.getBody()
                    .as(ResponseMessageDto.class);
            softAssert.assertEquals(response.getStatusCode(),
                    200, "validate status code");
            softAssert.assertTrue(responseMessageDto.getMessage()
                    .contains("deleted successfully"),
                    "validate message");
            softAssert.assertAll();
        }else
            Assert.fail("user don't have any cars");
    }

    @Test
    public void deleteCarNegativeTest_WrongSerialNumber(){
        Response response = deleteCarBySerialNumber("dvrtef");
        ErrorMessageDto errorMessageDto = response.getBody()
                .as(ErrorMessageDto.class);
        System.out.println(errorMessageDto);
        softAssert.assertEquals(response.getStatusCode(),
                400, "validate status code");
        softAssert.assertTrue(errorMessageDto.getMessage().toString()
                .contains("not found"), "validate message");
        softAssert.assertAll();
    }
}
