package api;

import helpers.ApiHelpers;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static config.ApiBodies.updatedBooking;
import static helpers.JsonHelpers.getBookingFirstName;
import static helpers.JsonHelpers.getBookingLastName;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Test(groups = "API")
public class ApiTests {

    @BeforeClass(alwaysRun = true)
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void getAllBookingsIsNotEmpty() {
        assertNotNull(ApiHelpers.getAllBookingIds());
    }

    @Test
    public void pingHealthCheck() {
        Response response = ApiHelpers.getHealthCheckPing();
        String responseValue = response.getBody().asString();
        assertEquals(responseValue, "Created");
    }

    @Test
    public void bookingCRUD() {
        // Create
        String bookingId = ApiHelpers.createBooking();

        // Update
        String token = ApiHelpers.getToken();
        ApiHelpers.updateBooking(updatedBooking, bookingId, token);

        // Read
        Response response = ApiHelpers.getBookingDetailsById(bookingId);
        assertEquals(getBookingFirstName(response), "Michael");
        assertEquals(getBookingLastName(response), "Jordan");

        // Delete
        Response deleteResponse = ApiHelpers.deleteBooking(bookingId, token);
        assertEquals(deleteResponse.getBody().asString(), "Created");

        Response verifyDeleteResponse = ApiHelpers.verifyBookingDoesNotExistById(bookingId);
        assertEquals(verifyDeleteResponse.getBody().asString(), "Not Found");
    }
}
