package lesson31;/*
Created by Pavel Gryshchenko on 23.11.2022
*/

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

/*
Используя сервис https://reqres.in/ убедиться, что операция LIST возвращает данные, отсортированные по годам.
 */
public class SortedYearsPOJO {
    private final static String URL = "https://reqres.in/";

    @Test
    public void checkYearsSorted() {
        Specification.installSpec(Specification.requestSpecification(URL), Specification.responseSpecification(200));

        List<YearsData> yearsDataList = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", YearsData.class);

        List<String> yearsActual = yearsDataList.stream().map(x -> x.getYear()).collect(Collectors.toList());
        List<String> yearsExpected = yearsActual.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(yearsActual, yearsExpected);
    }
}
