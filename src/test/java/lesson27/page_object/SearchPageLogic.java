package lesson27.page_object;/*
Created by Pavel Gryshchenko on 31.10.2022
*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPageLogic {
    By firstProductTitle = By.xpath("//span[@class='goods-tile__title']");
    private WebDriver driver;
    private WebDriverWait wait;

    SearchPageLogic(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    String firstProductTitleText() {
        wait.until(ExpectedConditions.presenceOfElementLocated(firstProductTitle)); //is this ok? decomposition?
        return driver.findElement(firstProductTitle).getText().trim();
    }

    ProductPageLogic clickOnFirstProduct() {
        driver.findElement(firstProductTitle).click();
        return new ProductPageLogic(driver, wait);
    }
}
