
package order;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import static org.hamcrest.CoreMatchers.notNullValue;
@RunWith(Parameterized.class)
public class OrderCreateTest {
    Faker faker = new Faker();
    private final String[] color;
    private OrderCreate orderCreate;
    private OrderCreateRequest orderCreateRequest;

    public OrderCreateTest(String[] color) {

        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getColors() {
        return new Object[][]{
                {new String[]{"BLACK", "GREY"}},
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{}}
        };
    }
    @Before
    public void setUp() {
        orderCreateRequest = new OrderCreateRequest();
    }
    @Test
    @DisplayName("Create order")
    @Description("Send post request to /api/v1/orders")
    public void createOrderWithDifferentColors() {
        orderCreate = new OrderCreate(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.address().fullAddress(),
                faker.number().digit(),
                faker.phoneNumber().phoneNumber(),
                faker.number().randomDigitNotZero(),
                "2022-09-22",
                faker.backToTheFuture().quote(),
                color);
        ValidatableResponse response = orderCreateRequest.create(orderCreate);
        response.assertThat().body("track", notNullValue());

    }
}
