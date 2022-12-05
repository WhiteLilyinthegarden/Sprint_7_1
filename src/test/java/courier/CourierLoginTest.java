package courier;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierLoginTest {
    private CourierInvalid courierInvalid;
    private CourierCredentials courierCredentials;
    private CourierCreate courierCreate;
    private CourierClient courierClient;
    private CourierDelete courierDelete;
    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }
    @Test
    @DisplayName("Courier authorization is successfully")
    @Description("Send post request to /api/v1/courier/login, expected status code 200 ok")
    public void courierAuthorization() {
        courierCredentials = new CourierCredentials("testuser1", "P@ssw0rd");
        createCourier();
        ValidatableResponse response = courierClient.login(courierCredentials);
        int statusCode = response.extract().statusCode();
        assertEquals(SC_OK, statusCode);
        response.assertThat().body("id", notNullValue());
        deleteCourier();
    }
    @Step("Create courier and return login")
    public void createCourier() {
        courierCreate = new CourierCreate("testuser1", "P@ssw0rd", "mily");
        courierClient.create(courierCreate);
    }
    @Step("Delete courier")
    public void deleteCourier() {
        courierCredentials = new CourierCredentials("testuser1", "P@ssw0rd");
        ValidatableResponse loginResponse = courierClient.login(courierCredentials);
        int loginId = loginResponse.extract().path("id");
        String idLogin = String.valueOf(loginId);
        courierDelete = new CourierDelete(idLogin);
        courierClient.delete(courierDelete, loginId);
    }
    @Test
    @DisplayName("Courier authorization return bad request")
    @Description("Send post request to /api/v1/courier/login, expected status code 400 bad request")
    public void courierAuthorizationErrorWithWrongData() {
        courierCredentials = new CourierCredentials("testuser1", "");
        ValidatableResponse response = courierClient.login(courierCredentials);
        int statusCode = response.extract().statusCode();
        assertEquals(SC_BAD_REQUEST, statusCode);
    }
    @Test
    @DisplayName("Courier authorization return not found")
    @Description("Sent post to /api/v1/courier/login, expected status code 404 not found")
    public void courierAccountNotFoundOrNotExist() {
        courierCredentials = new CourierCredentials("testuser1", "Passw0rd");
        ValidatableResponse response = courierClient.login(courierCredentials);
        int statusCode = response.extract().statusCode();
        assertEquals(SC_NOT_FOUND, statusCode);
    }
    @Test
    @DisplayName("Invalid request")
    @Description("If request doesn't have password key,  expected status code 504 gateway time-out")
    public void courierLoginInvalidRequest() {
        courierInvalid = new CourierInvalid("testuser1");
        ValidatableResponse response = courierClient.invalidLogin(courierInvalid);
        int statusCode = response.extract().statusCode();
        assertEquals(SC_GATEWAY_TIMEOUT, statusCode);
    }
}