package courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
public class CourierClient extends RestClient{
    @Step("Create courier with parameters {courier}")
    public ValidatableResponse create(CourierCreate createCourier) {
        return given()
                .spec(getBaseSpec())
                .body(createCourier)
                .when()
                .post("/api/v1/courier")
                .then();
    }
    @Step("Login with credentials {credentials}")
    public ValidatableResponse login(CourierCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }
    @Step("Delete courier with id {courierId}")
    public ValidatableResponse delete(CourierDelete courierDelete, int idCourier) {
        return given()
                .spec(getBaseSpec())
                .body(courierDelete)
                .when()
                .delete("/api/v1/courier/{idCourier}", idCourier)
                .then();
    }
    @Step("Courier authorization with invalid request")
    public ValidatableResponse invalidLogin(CourierInvalid invalidCredentials) {
        return given()
                .spec(getBaseSpec())
                .body(invalidCredentials)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }
}