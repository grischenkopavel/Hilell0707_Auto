package lesson19.my_first_test_run;/*
Created by Pavel Gryshchenko on 26.09.2022
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Locators {
    private WebDriver driver;

    @BeforeTest
    public void setUpTest()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void firstRun(){
        driver.get("https://rozetka.com.ua");
        WebElement logoXpath = driver.findElement(By.xpath("//img[@alt='Rozetka Logo']"));
        System.out.println(logoXpath.getSize());
        WebElement logoCss = driver.findElement(By.cssSelector("img[alt='Rozetka Logo']"));
        System.out.println(logoCss.isEnabled());

        WebElement searchBtnXpath = driver.findElement(By.xpath("//button[@class='button button_color_green button_size_medium search-form__submit ng-star-inserted']"));
        WebElement searchBtnXpath2 = driver.findElement(By.xpath("//button[contains(@class, 'button_color_green')]"));
        System.out.println(searchBtnXpath.getSize());
        WebElement searchBtnCss = driver.findElement(By.cssSelector("button[class='button button_color_green button_size_medium search-form__submit ng-star-inserted']"));
        System.out.println(searchBtnCss.getSize());

        WebElement userProfileBtnXpath = driver.findElement(By.xpath("//rz-user/button[@class='header__button ng-star-inserted']"));
        System.out.println(userProfileBtnXpath.getSize());
        WebElement userProfileBtnCss = driver.findElement(By.cssSelector("svg use[href='#icon-user-simple']"));
        System.out.println(userProfileBtnCss.getSize());


        driver.quit();
    }
}
