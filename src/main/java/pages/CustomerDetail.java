package pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;


public class CustomerDetail {

    private WebDriver driver;

    //Поле ввода имени
    private final By inputName = By.xpath(".//div[@class='Input_InputContainer__3NykH']//input[@placeholder='* Имя']");
    //Поле ввода фамилии
    private final By inputSurname = By.xpath(".//div[@class='Input_InputContainer__3NykH']//input[@placeholder='* Фамилия']");
    //Поле ввода адреса
    private final By inputStreet = By.xpath(".//div[@class='Input_InputContainer__3NykH']//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле выбора станции метро
    private final By inputMetro = By.xpath(".//div[@class='select-search__value']//input[@placeholder='* Станция метро']");
    //Поле ввода телефона
    private final By inputPhone = By.xpath(".//div[@class='Input_InputContainer__3NykH']//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Поле заголовка страницы
    private final By pageTitle = By.xpath(".//div[text()='Для кого самокат']");
    //Кнопка "Далее"
    private final By buttonNext = By.xpath(".//div[@class='Order_NextButton__1_rCA']//button[text()='Далее']");

    public CustomerDetail(WebDriver driver) {
        this.driver = driver;
    }

    // Проверка заголовка
    public String checkPageTitle () {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return driver.findElement(pageTitle).getText();

    }
    // Метод заполнения имени

    public void setInputName(String name) {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(inputName));
            driver.findElement(inputName).click();
            driver.findElement(inputName).sendKeys(name);

    }
    // Метод заполнения фамилии
    public void setInputSurname (String surname) {
        new WebDriverWait(driver,Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(inputSurname));
        driver.findElement(inputSurname).click();
        driver.findElement(inputSurname).sendKeys(surname);
    }
    // Метод заполнения адреса
    public void setInputStreet (String street) {
        new WebDriverWait(driver,Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(inputStreet));
        driver.findElement(inputStreet).click();
        driver.findElement(inputStreet).sendKeys(street);
    }
    // Метод выбора станции метро
    public void setInputMetro(String metro) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(inputMetro));
        WebElement station = driver.findElement(inputMetro);
        station.click();
        station.sendKeys(metro);
        station.sendKeys(Keys.ARROW_DOWN);
        station.sendKeys(Keys.ENTER);
    }
    // Метод заполнения телефона
    public void setInputPhone (String phone) {
        new WebDriverWait(driver,Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(inputPhone));
        driver.findElement(inputPhone).click();
        driver.findElement(inputPhone).sendKeys(phone);
    }
    // Метод нажатия на кнопку "Далее"
    public void clickButtonNext() {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(buttonNext));
        driver.findElement(buttonNext).click();
    }
}



