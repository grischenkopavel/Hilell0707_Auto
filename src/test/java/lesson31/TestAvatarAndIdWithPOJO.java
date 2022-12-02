package lesson31;/*
Created by Pavel Gryshchenko on 14.11.2022
*/

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;


/*
Используя сервис https://reqres.in/ получить список пользователей со второй(2) страницы
Убедиться что имена файлов-аватаров пользоваталей совпадают;
Убедиться, что email пользователей имеет окончание reqres.in;
 */
public class TestAvatarAndIdWithPOJO {
    private final static String URL = "https://reqres.in/";

    @Test
    public void checkAvatarAndId() {
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)//Вариант представления. В данном варианте вывод будет в формате JSON
                .get(URL + "api/users?page=2")
                .then().log().all()//вывод в консоль
                .extract().body().jsonPath().getList("data", UserData.class);

        users.stream().forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("reqres.in")));

        //второй способ собрать данные и выполнить проверку
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());

        for (int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }
}
