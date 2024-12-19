package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentPage {
    private WebDriver driver;
    // кнопка "Заказать" в заголовке страницы
    private By editDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");//

    // выпадающий список "Срок аренды"
    private By listPeriod = By.className("Dropdown-placeholder");

    // поле "Комментарий"
    private By editComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // кнопка  "Далее"
    private By buttonOrder = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    // кнопка  "Да" на модальном окне подтверждения заказа
    private By buttonConfirm = By.xpath(".//button[text()='Да']");

    // поле  "Заказ оформлен" на модальном окне с информацией о заказе
    private By textStatusOrder = By.className("Order_ModalHeader__3FDaJ");

    public RentPage(WebDriver driver){
        this.driver = driver;
    }

    public void setEditDate(String date) {
        driver.findElement(editDate).sendKeys(date);
        driver.findElement(editDate).sendKeys(Keys.ENTER);
    }

    public void setListPeriod(String period) {
        driver.findElement(listPeriod).click();
        driver.findElement(By.xpath(".//div[text()='"+period+"']")).click();
    }

    public void setCheckColour(String colour) {
        driver.findElement(By.id(colour)).click();
    }

    public void setEditComment(String comment) {
        driver.findElement(editComment).sendKeys(comment);
    }

    public void clickOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(buttonOrder));

        driver.findElement(buttonOrder).click();
    }

    public void clickConfirm() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(buttonConfirm));

        driver.findElement(buttonConfirm).click();
    }

    public void  fillRentPage (String date, String period, String colour, String comment){
        setEditDate(date);
        setListPeriod(period);
        setCheckColour(colour);
        setEditComment(comment);

        clickOrder();
    }

    public String getOrderStatus() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(textStatusOrder));

        return driver.findElement(textStatusOrder).getText();
    }

}
