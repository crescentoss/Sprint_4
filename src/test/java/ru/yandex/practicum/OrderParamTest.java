package ru.yandex.practicum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.practicum.pages.CustomerPage;
import ru.yandex.practicum.pages.HomePage;
import ru.yandex.practicum.pages.RentPage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.practicum.constants.Constants.Colours.*;
import static ru.yandex.practicum.constants.Constants.OrderButton.*;
import static ru.yandex.practicum.constants.Constants.RentalPeriod.*;
import static ru.yandex.practicum.constants.Constants.ServerName.URL;

@RunWith(Parameterized.class)
public class OrderParamTest {

    private final boolean isHeaderOrder;
    private final String name;
    private final String surName;
    private final String address;
    private final String station;
    private final String phone;
    private final String date;
    private final String period;
    private final String colour;
    private final String comment;

    WebDriver driver;


    public OrderParamTest(boolean isHeaderOrder, String name, String surName,
                          String address, String station, String phone,
                          String date, String period, String colour, String comment) {
        this.isHeaderOrder = isHeaderOrder;//
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {ORDER_BY_HEADER, "Иван", "Иванов", "г. Москва", "Полежаевская", "+79991111111", "11.01.2025", ONE_DAY, BLACK_PEARL, "за час позвонить"},
                {ORDER_BY_PAGE, "Петр", "Петров", "г. Москва", "Тушинская", "+79991111122", "12.02.2025", THREE_DAYS, GREY_DESPERATION, "оставить внизу"},
        };
    }

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        this.driver = new ChromeDriver(options);
//        this.driver = new ChromeDriver();

//        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
//        this.driver = new FirefoxDriver(options);
//        this.driver = new FirefoxDriver();
        driver.get(URL);
    }
    @Test
    public void createOrder(){

        HomePage homePage = new HomePage(driver);
        homePage.confirmCookie();

        homePage.orderByHeaderButton(isHeaderOrder);

        CustomerPage customerPage = new CustomerPage(driver);
        customerPage.fillCustomerData(name, surName, address, station, phone);

        RentPage rentPage = new RentPage(driver);
        rentPage.fillRentPage(date, period, colour, comment);
        rentPage.clickConfirm();

        assertThat(rentPage.getOrderStatus(), containsString("Заказ оформлен"));
    }
    @After
    public void tearDown(){
       driver.quit();
    }
}
