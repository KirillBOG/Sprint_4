import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.CustomerDetail;
import pages.HomePageObject;
import pages.OrderPage;
import pages.RentalConditions;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PlacingOrderTest {
    private WebDriver driver;
    private final String scooterUrl = "https://qa-scooter.praktikum-services.ru/";
    private final String button;
    private final String buttonNext;
    private final String name;
    private final String lastName;
    private final String adress;
    private final String metro;
    private final String phone;
    private final String date;
    private final String comment;

    public PlacingOrderTest(String button,  String name, String lastName, String adress, String metro, String phone, String date, String comment, String buttonNext) {
        this.button = button;
        this.buttonNext = buttonNext;
        this.name = name;
        this.lastName = lastName;
        this.adress = adress;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getDataOrder() {
        return new Object[][]{
                {"header","Артем", "Лаптев", "Москва, ул. Островитянова, д. 1", "Кузьминки", "+79998770001", "30.12.2024", "Оставить у двери","down"},
                {"home","Ярослав", "Золотов", "Москва, ул. Тверская", "Пушкинская", "+70012119596", "31.12.2024", "Во второй половине дня", "up"},

        };
    }


    @Before
    public void prepare() {
        driver = new FirefoxDriver();
        // driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get(scooterUrl);
    }

    @Test

    public void checkOrderScooter() throws InterruptedException {
        HomePageObject homePageObject = new HomePageObject(driver);
        homePageObject.clickСookieButton();
        homePageObject.clickOrderButtonOn(button);
        CustomerDetail customerDetail = new CustomerDetail(driver);
        String actualTitlePage = customerDetail.checkPageTitle();
        assertEquals("Неверный зголовок", "Для кого самокат", actualTitlePage);
        customerDetail.setInputName(name);
        customerDetail.setInputSurname(lastName);
        customerDetail.setInputStreet(adress);
        customerDetail.setInputMetro(metro);
        customerDetail.setInputPhone(phone);
        customerDetail.clickButtonNext();
        RentalConditions rentalConditions = new RentalConditions(driver);
        String actualTitleNextPage = rentalConditions.checkPageTitle();
        assertEquals("Неверный зголовок", "Про аренду", actualTitleNextPage);
        rentalConditions.deliveryArrivalDate(date);
        String period = rentalConditions.choiceRentalPeriod();
        String color = rentalConditions.setColorScooter();
        rentalConditions.inputComment(comment);
        rentalConditions.clickOrderButtonNew(buttonNext);
        rentalConditions.clickButtonYes();
        rentalConditions.clickButtonStatus();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.checkDataOrder(name, lastName, adress, metro, phone,date, period, color, comment);
        orderPage.ckickButtonCancelOrder();


    }

    @After
    public void closeDriver() {
        driver.quit();


    }
}

