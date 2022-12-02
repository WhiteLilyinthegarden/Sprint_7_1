package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class OrderListTest {
    private OrderListRequest orderListRequest;
    @Before
    public void setUp() {
        orderListRequest = new OrderListRequest();
    }
    @Test
    @DisplayName("Return order list")
    @Description("Send get request to /api/v1/orders")
    public void orderListReturns() {
        ValidatableResponse responseOrder = orderListRequest.getOrderList();
        ArrayList<String> orderForm = responseOrder.extract().path("orders");
        assertNotEquals(Collections.EMPTY_LIST, orderForm);
    }
}
