package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerPage {
    private WebDriver driver;
    // поле  "Имя"
    private By editName = By.xpath(".//input[@placeholder='* Имя']");

    // поле  "Фамилия"
    private By editSubName = By.xpath(".//input[@placeholder='* Фамилия']");

    // поле  "Адрес"
    private By editAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // поле  "Станция метро"
    private By editStation = By.xpath(".//input[@placeholder='* Станция метро']");

    // поле  "Контактный телефон"
    private By editPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // кнопка  "Далее"
    private By buttonSubmit = By.xpath(".//button[text()='Далее']");


    public CustomerPage(WebDriver driver){
        this.driver = driver;
    }

    public void setEditName(String name) {
        driver.findElement(editName).sendKeys(name);
    }

    public void setEditSubName(String subName) {
        driver.findElement(editSubName).sendKeys(subName);
    }

    public void setEditAddress(String address) {
        driver.findElement(editAddress).sendKeys(address);
    }

    public void setEditStation(String station) {
        driver.findElement(editStation).sendKeys(station);
        driver.findElement(editStation).sendKeys(Keys.DOWN, Keys.ENTER);
    }

    public void setEditPhone(String phone) {
        driver.findElement(editPhone).sendKeys(phone);
    }

    public void clickSubmit() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(buttonSubmit));

        driver.findElement(buttonSubmit).click();
    }

    public void fillCustomerData(String name, String subName, String address, String station, String phone) {

        setEditName(name);
        setEditSubName(subName);
        setEditAddress(address);
        setEditStation(station);
        setEditPhone(phone);

        clickSubmit();
    }
}
