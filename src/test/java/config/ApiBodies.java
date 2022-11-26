package config;

public class ApiBodies {

    public static String loginBody = "{\n" +
            "    \"username\" : \"admin\",\n" +
            "    \"password\" : \"password123\"\n" +
            "}";

    public static String createDefaultBooking =
            "{\n    \"firstname\" : \"Jim\",\n    \"lastname\" : \"Brown\",\n    \"totalprice\" : 111,\n    \"depositpaid\" : true,\n    \"bookingdates\" : {\n        \"checkin\" : \"2018-01-01\",\n        \"checkout\" : \"2019-01-01\"\n    },\n    \"additionalneeds\" : \"Breakfast\"\n}";

    public static String updatedBooking = "{\n    \"firstname\" : \"Michael\",\n    \"lastname\" : \"Jordan\"\n}";
}
