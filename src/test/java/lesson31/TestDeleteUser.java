package lesson31;/*
Created by Pavel Gryshchenko on 17.11.2022
*/
/*
Remove user id 2 from https://reqres.in/
Request /api/users/2
Response 204
 */

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestDeleteUser {
    private final static String URL = "https://reqres.in/";

    @Test
    public void testDelete() {
        Specification.installSpec(Specification.requestSpecification(URL), Specification.responseSpecification(204));

        given()
                .when()
                .delete("api/users/2")
                .then().log().all();
    }
}
