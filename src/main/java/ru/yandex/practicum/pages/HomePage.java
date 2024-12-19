package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    // кнопка "Заказать" в заголовке страницы
    private By headerOrder = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");

    // кнопка "Заказать" в середине страницы
    private By pageOrder = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    // кнопка "Подтвердить куки"
    private By buttonCookie = By.id("rcc-confirm-button");


    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void goToOrderPageByHeaderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(headerOrder));

        driver.findElement(headerOrder).click();
    }

    public void goToOrderPageByPageButton(){

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(pageOrder));

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(pageOrder));

        driver.findElement(pageOrder).click();
    }

    public void orderByHeaderButton(boolean flag){

        if(flag)
            goToOrderPageByHeaderButton();
        else
            goToOrderPageByPageButton();
    }

    public void confirmCookie(){
        driver.findElement(buttonCookie).click();
    }

}
