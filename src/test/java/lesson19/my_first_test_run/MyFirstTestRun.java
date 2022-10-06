package lesson19.my_first_test_run;/*
Created by Pavel Gryshchenko on 26.09.2022
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Message;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyFirstTestRun {
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
        System.out.println(logoXpath.toString());
        WebElement logoCss = driver.findElement(By.cssSelector("body > app-root > div > div > rz-header > rz-main-header > header > div > div > a > picture > img"));
        System.out.println(logoCss.getSize());

        WebElement searchBtnXpath = driver.findElement(By.xpath("//button[@class='button button_color_green button_size_medium search-form__submit ng-star-inserted']"));
        WebElement searchBtnXpath2 = driver.findElement(By.xpath("//button[@class='button button_color_green button_size_medium search-form__submit ng-star-inserted']"));
        System.out.println(searchBtnXpath.getSize());
        WebElement searchBtnCss = driver.findElement(By.cssSelector("body > app-root > div > div > rz-header > rz-main-header > header > div > div > div > form > button"));
        System.out.println(searchBtnCss.getSize());

        WebElement userProfileBtnXpath = driver.findElement(By.xpath("/html/body/app-root/div/div/rz-header/rz-main-header/header/div/div/ul/li[3]/rz-user/button"));
        System.out.println(userProfileBtnXpath.getSize());
        WebElement userProfileBtnCss = driver.findElement(By.cssSelector("body > app-root > div > div > rz-header > rz-main-header > header > div > div > ul > li.header-actions__item.header-actions__item--user > rz-user > button"));
        System.out.println(userProfileBtnCss.getSize());


        driver.quit();
    }
}
