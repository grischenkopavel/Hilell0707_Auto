package lesson26;/*
Created by Pavel Gryshchenko on 27.10.2022
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test_SelectAutoDoc {
    private WebDriver driver;
    private final String TEST_URL = "https://www.autodoc.de";

    @BeforeMethod
    void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TEST_URL);
    }

    @Test
    void selectTestAutodoc() throws InterruptedException {
        WebElement makerList = driver.findElement(By.id("form_maker_id"));
        WebElement makerListOption = driver.findElement(By.xpath("//select[@id='form_maker_id']/optgroup[2]/option[@value='2']"));

        makerList.click();
        makerListOption.click();
        TimeUnit.SECONDS.sleep(5);
    }

    @AfterTest
    void after() {
        driver.quit();
    }
}
