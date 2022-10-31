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

public class Test_SelectAutoDoc_reference {
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
        selectOption(driver, "5");
        TimeUnit.SECONDS.sleep(5);
    }

    void selectOption(WebDriver driver, String option) {
        WebElement makerList = driver.findElement(By.id("form_maker_id"));
        String makerListOption = String.format("//select[@id='form_maker_id']/optgroup[2]/option[@value='%s']", option);

        makerList.click();
        driver.findElement(By.xpath(makerListOption)).click();
    }

    @AfterTest
    void after() {
        driver.quit();
    }
}
