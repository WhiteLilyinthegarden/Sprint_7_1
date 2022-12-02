package order;

import courier.RestClient;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderCreateRequest extends RestClient {
    private static final String ORDER_PATH = "https://qa-scooter.praktikum-services.ru/api/v1/orders";
    public ValidatableResponse create(OrderCreate orderCreate) {
        return given()
                .spec(getBaseSpec())
                .body(orderCreate)
                .when()
                .post(ORDER_PATH)
                .then();
    }
}
