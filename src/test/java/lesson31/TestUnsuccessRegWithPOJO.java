package lesson31;/*
Created by Pavel Gryshchenko on 14.11.2022
*/
/*
Используя сервис https://reqres.in/ попробовать удалить второго пользователя и сравнить статус-код "error": "Missing password"
 */

import io.restassured.response.Response;
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
public class TestUnsuccessRegWithPOJO {
    private final static String URL = "https://reqres.in/";

    @Test
    public void TestSuccessReg() {
        String expectedError = "Missing password";

        Specification.installSpec(Specification.requestSpecification(URL), Specification.responseSpecification(400));

        Register register = new Register("sydney@fife");

        UnsuccessedUserReg unsuccessedUserReg = given()
                .body(register)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(UnsuccessedUserReg.class);

        Assert.assertNotNull(unsuccessedUserReg.getError());
        Assert.assertEquals(unsuccessedUserReg.getError(), expectedError);
    }
}
