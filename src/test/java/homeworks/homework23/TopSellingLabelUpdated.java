package homeworks.homework23;/*
Created by Pavel Gryshchenko on 06.10.2022
*/
/*
Перейти на https://rozetka.com.ua/
В сайд меню перейти в раздел Ноутбуки и компьютеры
Перейти в категорию Ноутбуки
Примерить фильтр "Продавец" Rozetka
Примерить фильтр "Цена" установть цену до - 100000
Перейти на продуктовую первого товара с плашкой " ТОП ПРОДАЖІВ"
Проверить что на продуктовой странице отображается плашка " ТОП ПРОДАЖІВ"
 */

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TopSellingLabelUpdated {
    private WebDriver driver;
    private final String ROZETKA_URL = "https://rozetka.com.ua/";
    private WebDriverWait wait;

    @BeforeTest
    void setUpTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    void RozetkaTest() {
        WebElement computersNotebooksLink = driver.findElement(
                (By.xpath("//a[@class='menu-categories__link']")));
        computersNotebooksLink.click();

        WebElement notebooksLink = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(@class, 'cats__heading tile')]")));
        notebooksLink.click();

        WebElement rozetkaSellerCheckbox = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[@data-id='Rozetka']")));
        rozetkaSellerCheckbox.click();

        WebElement label = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[@class='catalog-selection__link']")));

        WebElement maxCost = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//div[contains(@class, 'slider-filter__inner')]/input[2]")));
        maxCost.clear();
        maxCost.sendKeys("100000");

        WebElement buttonOk = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//div[contains(@class, 'slider-filter__inner')]/button")));
        buttonOk.click();

        WebElement firstProductWithTopSellingLabel = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//span[contains(@class, 'popularity')]//..")));
        firstProductWithTopSellingLabel.click();

        WebElement topSellingLabel = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//span[contains(@class, 'popularity')]")));
        Assert.assertTrue(topSellingLabel.isDisplayed(), "Top selling label do not exist");
    }

    @AfterTest
    void afterTest() {
        driver.quit();
    }
}
