package helpers;

import io.restassured.response.Response;

public class JsonHelpers {

    public static String getBookingFirstName(Response response) {
        return response.jsonPath().getString("firstname");
    }

    public static String getBookingLastName(Response response) {
        return response.jsonPath().getString("lastname");
    }
}
