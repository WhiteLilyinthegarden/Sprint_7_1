package order;

import courier.RestClient;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderListRequest extends RestClient {
    private static final String ORDER_PATH = "https://qa-scooter.praktikum-services.ru/api/v1/orders";
    public ValidatableResponse getOrderList() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }
}
