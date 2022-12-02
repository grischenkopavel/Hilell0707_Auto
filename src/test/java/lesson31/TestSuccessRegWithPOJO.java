package lesson31;/*
Created by Pavel Gryshchenko on 14.11.2022
*/

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


/*
Используя сервис https://reqres.in/ протестировать регистрацию пользователя в системе
Необходимо создание 2 тестов:
успешная регистрация.
Регистрация с ошибкой из-за отсутствия пароля.
Проверить коды ошибок.
 */
public class TestSuccessRegWithPOJO {
    private final static String URL = "https://reqres.in/";

    @Test
    public void TestSuccessReg() {
        Integer expectedId = 4;
        String expectedToken = "QpwL5tke4Pnpja7X4";

        Specification.installSpec(Specification.requestSpecification(URL), Specification.responseSpecification(200));

        Register register = new Register("eve.holt@reqres.in", "pistol");

        SuccessUserReq successUserReq = given()
                .body(register)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessUserReq.class);

        Assert.assertNotNull(successUserReq.getId());
        Assert.assertNotNull(successUserReq.getToken());

        Assert.assertEquals(successUserReq.getId(), expectedId);
        Assert.assertEquals(successUserReq.getToken(), expectedToken);
    }
}
