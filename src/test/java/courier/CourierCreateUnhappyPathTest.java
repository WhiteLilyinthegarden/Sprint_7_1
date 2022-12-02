package courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;

public class CourierCreateUnhappyPathTest {
    private CourierCreate courierCreate;
    private CourierClient courierClient;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }
    @Test
    @DisplayName("Not enough data for create account")
    @Description("Send post request to /api/v1/courier, expected status code 400 bad request")
    public void notEnoughDataForCreateCourierAccount() {
        courierCreate = new CourierCreate("", "P@ssword", "mily");
        ValidatableResponse response = courierClient.create(courierCreate);
        int statusCode = response.extract().statusCode();
        assertEquals(SC_BAD_REQUEST, statusCode);
    }
    @Test
    @DisplayName("Data already in use")
    @Description("Send post to /api/v1/courier, expected status code 409 conflict")
    public void loginForCreateCourierAccountAlreadyInUse() {
        courierCreate = new CourierCreate("testuser2", "Passw0rd", "lily");
        ValidatableResponse response = courierClient.create(courierCreate);
        int statusCode = response.extract().statusCode();
        assertEquals(SC_CONFLICT, statusCode);
    }
}