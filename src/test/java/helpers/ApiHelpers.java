package helpers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static config.ApiBodies.createDefaultBooking;
import static config.ApiBodies.loginBody;
import static io.restassured.RestAssured.given;

public class ApiHelpers {

    public static Response getAllBookingIds() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .extract().response();

        return response;
    }

    public static Response getBookingDetailsById(String bookingId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract().response();

        return response;
    }

    public static Response verifyBookingDoesNotExistById(String bookingId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(404)
                .extract().response();

        return response;
    }

    public static String getToken() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(loginBody)
                .post("/auth")
                .then()
                .statusCode(200)
                .extract().response();

        return response.jsonPath().getString("token");
    }

    public static String createBooking() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(createDefaultBooking)
                .post("/booking")
                .then()
                .statusCode(200)
                .extract().response();

        return response.jsonPath().getString("bookingid");
    }

    public static Response updateBooking(String updateData, String bookingId, String token) {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .body(updateData)
                .patch("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract().response();

        return response;
    }

    public static Response deleteBooking(String bookingId, String token) {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201)
                .extract().response();

        return response;
    }

    public static Response getHealthCheckPing() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/ping")
                .then()
                .statusCode(201)
                .extract().response();

        return response;
    }
}
