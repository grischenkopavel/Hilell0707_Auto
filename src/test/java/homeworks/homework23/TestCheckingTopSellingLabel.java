package homeworks.homework23;/*
Created by Pavel Gryshchenko on 06.10.2022
*/
/*
Example from Alex https://pastebin.com/LwFb2D3B
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

public class TestCheckingTopSellingLabel {
    private WebDriver driver;
    private final String ROZETKA_URL = "https://rozetka.com.ua/";
    private WebDriverWait wait;

    @BeforeTest
    void setUpTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(ROZETKA_URL);
    }

    @Test
    void RozetkaTest() {
        WebElement computersNotebooksLink = driver.findElement(
                (By.xpath("//a[@class='menu-categories__link']")));
        computersNotebooksLink.click();

        WebElement notebooksLink = driver.findElement(By.xpath("//*[contains(@href, 'c80004')]"));
        notebooksLink.click();

        By rozetkaSellerCheckbox = By.xpath("//a[@data-id='Rozetka']");
        wait.until(ExpectedConditions.elementToBeClickable(rozetkaSellerCheckbox));
        myClick(rozetkaSellerCheckbox);

        WebElement rozetkaSellerLabel = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[@class='catalog-selection__link']")));

        WebElement maxCost = driver.findElement
                (By.xpath("//div[contains(@class, 'slider-filter__inner')]/input[2]"));
        wait.until(ExpectedConditions.visibilityOf(maxCost));
        maxCost.clear();
        maxCost.sendKeys("100000");

        WebElement buttonOk = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//div[contains(@class, 'slider-filter__inner')]/button")));
        buttonOk.click();

        driver.navigate().refresh();

        By firstTopProduct = By.xpath("//span[contains(@class, 'promo-label_type_popularity')]/../a");
        wait.until(ExpectedConditions.elementToBeClickable(firstTopProduct));
        String urlOfFirstTopProduct = driver.findElement(firstTopProduct)
                .getAttribute("href");
        myClick(firstTopProduct);

        wait.until(ExpectedConditions.urlToBe(urlOfFirstTopProduct));
        WebElement topLabelOnProductPage = driver.findElement(By.xpath("//span[contains(@class, 'promo-label_type_popularity')]"));
        Assert.assertTrue(topLabelOnProductPage.isDisplayed());
    }

    @AfterTest
    void afterTest() {
        driver.quit();
    }

    public void myClick(By element) {
        try {
            driver.findElement(element).click();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            System.out.print("HERE " + element);
            driver.findElement(element).click();
        }
    }
}
