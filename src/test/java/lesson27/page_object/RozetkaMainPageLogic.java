package lesson27.page_object;/*
Created by Pavel Gryshchenko on 31.10.2022
*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaMainPageLogic {
    By searchInput = By.name("search");
    By findBtn = By.xpath("//button[contains(@class, 'button_color_green')]");
    private WebDriver driver;
    private WebDriverWait wait;

    public RozetkaMainPageLogic(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    RozetkaMainPageLogic typeTextToSearchInput(String text) {
        driver.findElement(searchInput).sendKeys(text);
        return this;
    }

    SearchPageLogic clickFindBtn() {
        driver.findElement(findBtn).click();
        return new SearchPageLogic(driver, wait);
    }
}
