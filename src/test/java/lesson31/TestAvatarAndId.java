package lesson31;/*
Created by Pavel Gryshchenko on 14.11.2022
*/

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/*
Используя сервис https://reqres.in/ получить список пользователей со второй(2) страницы
Убедиться что имена файлов-аватаров пользоваталей совпадают;
Убедиться, что email пользователей имеет окончание reqres.in;
 */
public class TestAvatarAndId {
    private final static String URL = "https://reqres.in/";

    @Test
    public void checkAvatarAndId() {
        Response response = given()
                .when()
                .get(URL + "api/users?page=2")
                .then().log().all()//вывод в консоль
                .body("page", equalTo(2))
                .body("data.id", notNullValue())
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.avatar", notNullValue())
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<Integer> ids = jsonPath.get("data.id");
        List<String> emails = jsonPath.get("data.email");
        List<String> avatars = jsonPath.get("data.avatar");

        for (int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
        }

        Assert.assertTrue(emails.stream().allMatch(x -> x.endsWith("reqres.in")));
    }
}
